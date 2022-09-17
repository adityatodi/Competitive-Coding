import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Meta_Hackathon_Practice_ZigZag {
    public static int iter = 1;
    public static void main(String args[])throws IOException {
        Path fileName = Path.of("/Users/adityatodi/Documents/Java Repo/Programming/src/zigzag_hard_input.txt");
        String str = Files.readString(fileName, StandardCharsets.US_ASCII);
        System.out.println(str);


        Long sum = 0l;
        String[] splitArray = str.split("\n");
        int noGrids = Integer.parseInt(splitArray[0]);
        int ind = 1;
        for (int i=0; i<noGrids; i++) {
            int[][] grid = new int[9][9];
            for (int j=0; j<9;j++) {
                String[] row = splitArray[ind++].split(" ");
                for (int k=0; k<row.length; k++) {
                    if (row[k].equals("."))
                        grid[j][k] = -1;
                    else
                        grid[j][k] = Integer.parseInt(row[k]);
                }
            }
            ind++;
            sum += findTopLeft(grid);
        }


        // parsing the String argument as a signed decimal
        // integer object and storing that integer into the
        // array
        File path = new File("/Users/adityatodi/Documents/Java Repo/Programming/zigzag_answer.txt");
        FileWriter wr = new FileWriter(path);
        wr.write(sum.toString());
        wr.flush();
        wr.close();
    }

    private static int findTopLeft(int[][] grid) {
        if (grid[0][0] != -1)
            return grid[0][0];
        int[] digits = new int[9];
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                if (grid[i][j] != -1) {
                    if (i == j || i + j == 9 || (i<=2 && j<=2))
                        digits[grid[i][j]]++;
                }
            }
        }
        System.out.println("Grid " + iter);
        for(int[] row: grid) {
            for (int cell : row)
                System.out.print(cell + "\t");
            System.out.println();
        }
        System.out.println("Digits Array for Grid " + iter + " = " + Arrays.toString(digits));
        for(int i=0; i<9; i++)
            if (digits[i] == 0)
                return i;
        return 0;
    }
}
