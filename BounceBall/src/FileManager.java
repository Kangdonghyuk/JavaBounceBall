import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class FileManager {
    private int [][] map;

    public FileManager() {
        int yIndex, xIndex;

        map = new int[36][];
        for(yIndex = 0; yIndex < 36; yIndex++) {
            map[yIndex] = new int[60];
            for(xIndex = 0; xIndex < 60; xIndex++)
                map[yIndex][xIndex] = 0;
        }
    }

    public void readStage(int stage) {
        String fileName = "src" + File.separator+"stage" +
                File.separator + "stage" + stage + ".txt";
        System.out.println(fileName);
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);

            int readChar;
            int index = 0;

            while((readChar = fileReader.read()) != -1) {
                if(readChar >= 48 && readChar <= 58) {
                    map[index / 60][index % 60] = readChar-48;
                    index++;
                }
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public int[][] getMap() {
        return map;
    }
}
