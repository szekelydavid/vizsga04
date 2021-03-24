package com.codecool.chessopen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChessResults {


    public List<String> getCompetitorsNamesFromFile(String fileName) {

        try {
            HashMap<String, Integer> eredmenyekMap = new HashMap<String, Integer>();

            BufferedReader reader;
            FileReader fr = null;
            fr = new FileReader(fileName);
            reader = new BufferedReader(new FileReader(fileName));


            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }

                String[] sorsplit = line.split(",");
                String versenyzoNeve = sorsplit[0];
                int osszesPont = 0;
                for (int i = 1; i < sorsplit.length; i++) {
                    int egyesEredmeny = Integer.valueOf(sorsplit[i]);
                    osszesPont += egyesEredmeny;
                }
                eredmenyekMap.put(versenyzoNeve, osszesPont);
            }


            Map<String, Integer> rendezettEredmenyek =
                    eredmenyekMap.entrySet().stream()
                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            List<String> eredmenyReturnString = new ArrayList<String>();
            Iterator it = rendezettEredmenyek.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                eredmenyReturnString.add((String) pair.getKey());
                it.remove();
            }
            return eredmenyReturnString;
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        return null;
    }
}
