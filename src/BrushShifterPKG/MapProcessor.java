package BrushShifterPKG;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class MapProcessor
{
    //This string will hold the name of the map file (or the path)
    private String mapFile;

    //These integers will hold the shift values on the respective axis
    private int xShift, yShift, zShift;

    //This scanner will perform scans on the map file
    private Scanner mapScanner;

    //This String array will hold the lines of a map file
    private String[] lines;

    //This scanner will check that the map file is from Reflex, the boolean will hold whether or not it is
    private Scanner reflexValidator;
    public boolean reflexValidated = false;

    //This will take care of writing the new file
    private FileWriter mapWriter;

    /**
     * This is the constructor for the map processor
     * @param mapFile The map file to be processed
     */
    public MapProcessor(String mapFile) throws IOException
    {
        validateReflexMapFile(mapFile);

        if (reflexValidated)
        {
            //Set the map file parameter
            this.mapFile = mapFile;

            //Initialize the map scanner
            mapScanner = new Scanner(new File(mapFile));

            //Tell the user everything is honky dory for the scanner
            System.out.println("\nLoaded your file: " + mapFile);

           //The name of the file with the modification in the name
           String partialFileName = mapFile.substring(0, mapFile.length() - 4);

           //Initialize the file writer
           mapWriter = new FileWriter(partialFileName + "_BrushShifted.map");

           //Tell the user everything is honky dory for the writer
           System.out.println("Initialized empty map file for writing: " + partialFileName + "_BrushShifted.map");

           lines = new String[lineCount()];
           System.out.println("Created array of lines with size " + lines.length + "\n");
        }
    }

    /**
     * Perform tasks and call helper methods necessary to shift every brush in the map by a determined integer amount
     * @param xShift The amount to shift in the X axis
     * @param yShift The amount to shift in the Y axis
     * @param zShift The amount to shift in the Z axis
     * @throws IOException
     */
    public void shiftMap(int xShift, int yShift, int zShift) throws IOException
    {
        createLinesArray();
        
        //Close mapScanner (not necessary after this point)
        mapScanner.close();

        //These two integers will hold the values for which a gap will be left for the vertex groups
        //Initialized to unreachable values
        int startGap = lines.length + 1;
        int endGap = lines.length + 1;

        //This process will find the start and end of a vertex group (in between "vertices" and "faces") and call the helper method
        for (int i = 0; i < lines.length; i++)
        {
            if (lines[i].contains("vertices"))
            {
                for (int j = i + 1; j < lines.length; j++)
                {
                    if (lines[j].contains("faces"))
                    {
                        //Call the helper method with the start and end
                        processVerts(i + 1, j, xShift, yShift, zShift);

                        //Set the start and end of the gap
                        startGap = i;
                        endGap = j;

                        //Break this loop
                        break;
                    }
                }
            }

            //If the current line falls between the gap, skip this iteration of the loop
            else if (i > startGap && i < endGap)
            {
                continue;
            }

            //Else, write the lines normally
            else
            {
                mapWriter.write(lines[i] + "\n");
            }
        }

        //Close mapWriter
        mapWriter.close();
    }

    /**
     * This method will get called during construction to determine the size of the lines array
     * @return The number of lines in the map file
     * @throws IOException
     */
    private int lineCount() throws IOException
    {
        //This integer will hold the amount of lines
        int lines = 0;

        //Create a temporary scanner to iterate through the map file
        Scanner lineCounter = new Scanner(new File(mapFile));

        //Iterate through the map file and increment the counter per each line
        while(lineCounter.hasNextLine())
        {
            lineCounter.nextLine();
            lines++;
            //System.out.println("I am at line" + lines);
        }
        
        //Close lineCounter
        lineCounter.close();

        //Spit out the amount of lines
        return lines;
    }

    /**
     * This method will populate the lines array with each line of the map file
     */
    private void createLinesArray()
    {
        //This will iterate through the lines array
        for (int i = 0; i < lines.length; i++)
        {
            //Stop at end-of-File
            if (mapScanner.hasNextLine())
            {
                //Add the current line to the lines array
                lines[i] = mapScanner.nextLine();
            }
        }
    }

    /**
     * When called, this method will extract the vertices
     */
    private void processVerts(int start, int end, int xShift, int yShift, int zShift) throws IOException
    {
        //These doubles will hold the coordinates of a vertex
        double vertX, vertY, vertZ;

        //Tell user where the extraction is happening
        System.out.println("Processing Vertex Group: " + start + " to " + end + "\n");

        //This is to print the "vertices" line in the file, do not touch or the entire formatting will be off
        mapWriter.write("\t\tvertices\n");

        //This will be the new line written to the map file, holding shifted and formatted values
        String newLine;

        //Iterate through the vertex group
        for (int i = start; i < end; i++)
        {
            //This scanner will take care of reading each line of the vertex group (each lines holds an x, y, z)
            //Feed the line into the scanner
            Scanner vertScanner = new Scanner(lines[i]);

            //Extract 3 doubles and throw them into the container doubles
            vertX = Double.parseDouble(String.valueOf(vertScanner.nextDouble())) + xShift;
            vertY = Double.parseDouble(String.valueOf(vertScanner.nextDouble())) + yShift;
            vertZ = Double.parseDouble(String.valueOf(vertScanner.nextDouble())) + zShift;

            //Format the values into the new line
            newLine = String.format("\t\t\t%.6f %.6f %.6f\n", vertX, vertY, vertZ);

            //System.out.println(newLine);

            //Write the new line into the map file
            mapWriter.write(newLine);
            
            //Close vertScanner
            vertScanner.close();
        }
    }

    /**
     * This method will perform a validation check to make sure the map file is from Reflex Arena
     * @param mapToCheck The name of the map file to validate
     * @throws FileNotFoundException
     */
    private void validateReflexMapFile(String mapToCheck) throws FileNotFoundException
    {
        //Initialize the map scanner
        reflexValidator = new Scanner(new File(mapToCheck));

        if (reflexValidator.next().contains("reflex"))
        {
            reflexValidated = true;
            System.out.println("\nThis is a valid Reflex Arena map file");
        }

        else
        {
            System.out.println("\nThis is not a Reflex Arena map file");
        }
    }
}
