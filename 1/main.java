import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

class Lab1 {
    public static void main(String[] args) {
        HashSet<String> consonant = new HashSet<>();
        //NOT (A, E, I, O, U, Y)
        consonant.add("a");
        consonant.add("e");
        consonant.add("i");
        consonant.add("o");
        consonant.add("u");
        consonant.add("y");
        File file = new File(args[0]);
        Map<String, Integer> words = new HashMap< String, Integer>();
        try{
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("[^a-zA-Z’-]");
            while (scanner.hasNext()){
                String tmp = scanner.next();
                if(tmp.length() <= 1)continue;
                Integer k = 0;
                for (int i = 0; i < tmp.length(); ++i){
                    Character c = Character.toLowerCase(tmp.charAt(i));
                    String a = Character.toString(c);
                    if(consonant.contains(a) != true){
                        ++k;
                    }
                }
                if(words.get(tmp) == null){
                    words.put(tmp, k);
                }
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        words = sortByComparator(words);
        for (Map.Entry entry: words.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap){

        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Entry<String, Integer>>(){
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2){
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list){
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}

