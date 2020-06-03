import java.util.*;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.io.FileNotFoundException;

public class UseCaseName {

	public static void main(String[] args) throws IOException {
		JSONObject jsonobj = new JSONObject();
		JSONObject jsonobj1 = new JSONObject();
		JSONObject jsonobj2 = new JSONObject();
		double[] array = new double[800];
		File file = new File("F:\\CPU.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String readline;
			String name, name1, storingvalue, finalvalue;
			int i = 0, j, value = 0;
			int count = 0, begin = 0;
			double sample, max = 0.0, sum = 0.0;
			while ((readline = br.readLine()) != null) {
				count += 1;
				StringTokenizer st = new StringTokenizer(readline);
				while (st.hasMoreTokens()) {
					name1 = st.nextToken();
					begin += 1;
					if (begin == 9) {
						storingvalue = name1;
						sample = Double.parseDouble(storingvalue);
						array[i++] = sample;
						begin = 0;
						break;
					}
				}
			}
			for (j = 0; j < count; j++) {
				value = j + 1;
				name = String.format("%d", value);
				finalvalue = String.format("%.1f", array[j]);
				jsonobj1.put(name + "s", finalvalue);
				if (array[j] > max) {
					max = array[j];
				}
				sum = sum + array[j];
				value = 0;
			}
			double avg = sum / count;
			String totalavg = String.format("%.2f", avg);
			String maximum = String.format("%.2f", max);
			jsonobj.put("values: ", jsonobj1);
			jsonobj.put("avgcpu", avg);
			jsonobj.put("maxcpu", maximum);
			jsonobj2.put("sampletransaction", jsonobj);
			JSONArray jsonList = new JSONArray();
			jsonList.add(jsonobj2);
			try (FileWriter file1 = new FileWriter("F://OutputFile.json")) {
				file1.write(jsonList.toString());
				file1.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
