import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> listInput = readAllLines("input6.txt");

        String timeRow = listInput.get(0);
        String distanceRow = listInput.get(1);
        int idChar = timeRow.indexOf(":");
        String numbers = timeRow.substring(idChar+2,timeRow.length()).trim();
        String[] timeRowArr = numbers.split("\\s+");
        int finalResult = 1;

        idChar = distanceRow.indexOf(":");
        numbers = distanceRow.substring(idChar+2,timeRow.length()).trim();
        String[] distanceRowArr = numbers.split("\\s+");

        long[][] timeDistanceArr = loadArray(timeRowArr,distanceRowArr);
        long  numberOfWays = 1;

        for (int i = 0; i < timeDistanceArr.length+1; i++) {
            long timeRace = timeDistanceArr[0][i];
            long distanceRace = timeDistanceArr[1][i];

            numberOfWays = findWays(timeRace,distanceRace);
            System.out.println();
            System.out.println(numberOfWays);
            finalResult *= numberOfWays;

        }
    System.out.printf("Výsledek je %d",finalResult);

    }
    public static long findWays(long timeRace,long distanceRace){
        int result = 0;

        for (int i = 1; i < timeRace; i++) {

            long timeRest = timeRace-i;
            long finalDistance = timeRest*i;
            if (finalDistance>=distanceRace){
                result += 1;
                System.out.print(i+" ");
            }
        }
        return result;
    }

    public static long[][] loadArray(String[] array1,String[] array2){
        long[][] result = new long[2][array1.length];

        for (int i = 0; i < array1.length; i++) {
            result[0][i] = Integer.parseInt(array1[i]);
            result[1][i] = Integer.parseInt(array2[i]);
        }
       return result;
    }

    public static List<String> readAllLines(String resource)throws IOException {
        ClassLoader classLoader=Thread.currentThread().getContextClassLoader();

        try(InputStream inputStream=classLoader.getResourceAsStream(resource);
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))){
            return reader.lines().collect(Collectors.toList());
        }
    }
}