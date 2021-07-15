import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sample {
    public static void main(String[] args)
    {
        String in = "//[*][%]\\n-1*2%-3";

        Pattern p = Pattern.compile("\\[(.*?)\\]");
        Matcher m = p.matcher(in);
        String delimiter = "";
        while(m.find()) {
            delimiter += Pattern.quote(m.group(1)) + "|";
        }
        delimiter = delimiter.substring(0, delimiter.length() - 1);
        String[] arr = in.split(delimiter);
        List<String> list = new LinkedList<String>();
        for(int i = 0; i < arr.length; i++){
            if(arr[i].contains("n")){
                String[] temp = arr[i].split("n");
                arr[i] = temp[temp.length - 1];
            }
            if(arr[i].matches(".*\\d.*")){
                list.add(arr[i]);
            }
        }
        String[] ans = list.toArray(new String[0]);
    }
}
