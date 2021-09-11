import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    public static int hourglassSum(List<List<Integer>> arr) {
        // System.out.println(arr);
        int sumHigh = -63;  /* Since the constraints specify that any value of the Hourglass pattern in the 
                             2D Array would be within -9 <= value <= 9, we can assume that the least of the 
                             sum can only go upto -63 (i.e., 7 values in an Hourglass pattern and 7 times (- or +) 9) */
        
        for (int outer = 0; outer < arr.size() - 2; outer++) {
            for (int inner = 0; inner < arr.get(outer).size() - 2; inner++) {
                int sumNow = 0;
                
                // First row in the Hourglass (3 elements)
                for (int in_inner = inner; in_inner <= inner + 2; in_inner++) {
                    // System.out.println("First row: " + arr.get(outer).get(in_inner));
                    sumNow += arr.get(outer).get(in_inner);
                }
                
                // Second row in the Hourglass (1 element)
                // System.out.println("Second row: " + arr.get(outer + 1).get(inner + 1));
                sumNow += arr.get(outer + 1).get(inner + 1);
                
                // Third row in the Hourglass (3 elements)
                for (int in_innerNextNext = inner; in_innerNextNext <= inner + 2; in_innerNextNext++) {
                    // System.out.println("Third row: " + arr.get(outer + 2).get(in_innerNextNext));
                    sumNow += arr.get(outer + 2).get(in_innerNextNext);
                }
                
                // System.out.println("Sum, for now: " + sumNow);
                
                if (sumNow > sumHigh) {
                    sumHigh = sumNow;
                }
                // System.out.println("Highest sum yet: " + sumHigh);
                // System.out.println("///");
            }
        }
        return sumHigh;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, 6).forEach(i -> {
            try {
                arr.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
