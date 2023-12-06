import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class Main2 {
    public static void main(String[] args) throws IOException {
        List<String> listInput = readAllLines("input6gold.txt");

        String timeRow = listInput.get(0);
        String distanceRow = listInput.get(1);
        int idChar = timeRow.indexOf(":");
        String numbers = timeRow.substring(idChar+2,timeRow.length()).trim();
        String timeRowArr = numbers.trim();
        int finalResult = 1;

        idChar = distanceRow.indexOf(":");
        numbers = distanceRow.substring(idChar+2,timeRow.length()).trim();
        String distanceRowArr = numbers.trim();


        int timeRace = Integer.parseInt(timeRowArr);
        int distanceRace = Integer.parseInt(distanceRowArr);

        int numberOfWays = 1;

        numberOfWays = findWays(timeRace,distanceRace,14,71516);
        finalResult *= numberOfWays;


    System.out.printf("Výsledek je %d",finalResult);

    }
    public static int findWays(int timeRace,int distanceRace,int from, int last){
        int result = 0;

        for (int i = from; i <= last; i++) {

            int timeRest = timeRace-i;
            int finalDistance = timeRest*i;
            if (finalDistance>distanceRace){
                result += 1;
                //System.out.print(i+" ");
            }
        }
        return result;
    }

    public static int[][] loadArray(String[] array1,String[] array2){
        int[][] result = new int[2][array1.length];

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