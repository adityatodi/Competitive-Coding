import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Meta_Hackathon_Practice_rollercoster {
    public static void main(String args[])throws IOException {
        Path fileName = Path.of("/Users/adityatodi/Documents/Java Repo/Programming/src/rollercoasters_medium_input.txt");
        String str = Files.readString(fileName, StandardCharsets.US_ASCII);
        String[] splitArray = str.split("\n");
        int[] arr = new int[splitArray.length];

        // parsing the String argument as a signed decimal
        // integer object and storing that integer into the
        // array
        for (int i = 0; i < splitArray.length; i++)
            arr[i] = Integer.parseInt(splitArray[i]);
        Long ans =  findMaxIncreasingSubArray(arr);
        System.out.println(ans);
        File path = new File("/Users/adityatodi/Documents/Java Repo/Programming/rollercoaster_answer.txt");
        FileWriter wr = new FileWriter(path);
        wr.write(ans.toString());
        wr.flush();
        wr.close();
    }

    private static long findMaxIncreasingSubArray(int[] arr) {
        int n = arr.length;
        int count = 1;
        int maxCount = Integer.MIN_VALUE;
        int curr = arr[0];
        for(int i=1; i<n; i++) {
            if (arr[i] > arr[i-1]) {
                count++;
            }
            else {
                maxCount = Math.max(maxCount, count);
                count = 1;
            }
        }
        maxCount = Math.max(maxCount, count);
        return maxCount*10;
    }
}
