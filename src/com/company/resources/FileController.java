package com.company.resources;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileController {
    private final File modifiedFile;
    private final String path;
    private List<String> lines;

    public FileController(String filePath) throws IOException {
        path = getClass().getResource(filePath).getPath();
        modifiedFile = new File(path);

        if(!modifiedFile.exists())
            System.out.println("File not exist");

        lines = readFileToList();
    }

    private List<String> readFileToList() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(modifiedFile);
        List<String> lines = new ArrayList<>();

        while (fileScanner.hasNextLine()){
            String line = fileScanner.nextLine();
            if(!line.isBlank())
                lines.add(line);
        }
        fileScanner.close();
        return lines;
    }

    private int searchLineIndex(String searchedLine){
        int index = 0;
        for (String line : lines){
           if(line.equals(searchedLine))
                break;
           index++;
        }
        return index;
    }

    private void writeFile() throws IOException {
        FileWriter writer = new FileWriter(modifiedFile);
        String content = "";
        for(String line : lines){
            content += line + System.lineSeparator();
        }

        writer.write(content);
        writer.close();
    }

    public void deleteLine(String line) throws IOException {
        int index = searchLineIndex(line);
        lines.remove(index);
        writeFile();
    }

    public void addLine(String line) throws IOException {
        lines.add(line);
        writeFile();
    }

    public void replaceLine(String oldLine, String newLine) throws IOException {
        int index = searchLineIndex(oldLine);
        lines.set(index, newLine);
        writeFile();
    }

    public void displayLines(){ lines.forEach(System.out::println); }

    public List<String> getLines() { return lines; }

}
