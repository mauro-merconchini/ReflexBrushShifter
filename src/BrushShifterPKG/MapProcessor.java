package BrushShifterPKG;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MapProcessor
{
    //This string will hold the name of the map file (or the path)
    private String mapFile;

    //These integers will hold the shift values on the respective axis
    private int xShift, yShift, zShift;

    //This scanner will perform scans on the map file
    private Scanner mapScanner;

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
            System.out.println("Loaded your file: " + mapFile);

            //Initialize the file writer
            mapWriter = new FileWriter(mapFile.substring(0, mapFile.length() - 4) + "_processed.map");

            //Tell the user everything is honky dory for the writer
            System.out.println("Initialized empty map file for writing: " + "processed_" + mapFile);
        }
    }

    /**
     * This method will read through the map file, pull out the vertex values, and call MapMather
     */
    public void extractVerts()
    {

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
            System.out.println("This is a valid Reflex Arena map file");
        }

        else
        {
            System.out.println("This is not a Reflex Arena map file");
        }
    }
}
