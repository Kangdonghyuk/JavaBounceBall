import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class FileManager {
    public static FileManager I;
    private int [][] map;
    private int startXPosition, startYPosition;

    public FileManager() {
        I = this;
    }

    public File readFile(String fileName) {
            File file = new File(fileName);
            return file;
    }
    public BufferedImage getImage(String fileName) {
        try {
            return ImageIO.read(readFile(fileName));
        } catch (Exception e) {
            return null;
        }
    }

    public void readStage(int stage) {
        String fileName = "src" + File.separator+"stage" +
                File.separator + "stage" + stage + ".txt";

        try {
            FileReader fileReader = new FileReader(readFile(fileName));

            int readChar;
            int index = 0;
            int blockSize = Information.I.blockSize;
            int yIndex, xIndex;
            int yMaxIndex = Information.I.yMaxIndex, xMaxIndex = Information.I.xMaxIndex;

            map = null;
            map = new int[yMaxIndex][];
            for(yIndex = 0; yIndex < yMaxIndex; yIndex++) {
                map[yIndex] = new int[xMaxIndex];
                for(xIndex = 0; xIndex < xMaxIndex; xIndex++)
                    map[yIndex][xIndex] = 0;
            }

            while((readChar = fileReader.read()) != -1) {
                if(readChar >= 48 && readChar <= 58) {
                    map[index / xMaxIndex][index % xMaxIndex] = readChar-48;
                    if(readChar-48 == 8) {
                        startXPosition = index % xMaxIndex * blockSize;
                        startYPosition = index / xMaxIndex * blockSize;
                        map[index / xMaxIndex][index % xMaxIndex] = 0;
                    }
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
    public int getStartXPosition() {
        return startXPosition;
    }
    public int getStartYPosition() {
        return startYPosition;
    }
}
