import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.io.FileNotFoundException;

import org.json.JSONException;
import org.json.JSONObject;
public class UseCaseName {

	public static void main(String[] args) throws IOException, JSONException 
	{
		double[] d = new double[10000];
		JSONObject obj  = new JSONObject();
		JSONObject obj1 = new JSONObject();
		File file = new File("F:\\Memory.txt");
		try (BufferedReader bf = new BufferedReader(new FileReader(file))) 
        {
            String readLine;
            double value;
            int line = 0;
            int i=0;
            double sum=0.0;
            double max = 0.0;
            while ((readLine = bf.readLine()) != null) 
            {
                if (line % 2 != 0) {
                	String s=readLine;
                	s=s.replaceAll("[^0-9]","");
                    s=s.trim();
                    value=Integer.parseInt(s);
                    d[i++]=value/10000;
                }
                line++;
            }
            String s1;
            for(int j=0;j<938;j++)
            {
            	s1 = String.format("%d",j);
            	obj1.put(s1 + "s", d[j]);
            	if(max<d[j])
            		max=d[j];
            	sum=sum+d[j];
            }
            double average=sum/938;
            DecimalFormat df = new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.CEILING);
            obj.put("AverageMemory(MB)", df.format(average));
            obj.put("values: ", obj1);
            obj.put("MaximumMemory(MB)", df.format(max));
            System.out.println(obj);
         }
 }
}
