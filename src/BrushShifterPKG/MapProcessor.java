package BrushShifterPKG;

import java.io.File;
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

    //This will take care of writing the new file
    private FileWriter mapWriter;

    /**
     * This is the constructor for the map processor
     * @param mapFile The map file to be processed
     */
    public MapProcessor(String mapFile) throws IOException
    {
        //Set the map file parameter
        this.mapFile = mapFile;

        //Initialize the map scanner
        mapScanner = new Scanner(new File(mapFile));

        //Initialize the file writer
        mapWriter = new FileWriter("processed_" + mapFile);

        //Tell the user everything is honky dory
        System.out.println("Found your file: " + mapFile);
    }


}
