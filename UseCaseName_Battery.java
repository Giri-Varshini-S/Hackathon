import java.util.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class UseCaseNameBattery {

	public static void main(String[] args) throws IOException {
		JSONObject jsonobj = new JSONObject();
		File file = new File("F:\\Battery.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String readLine;
			String first, value = "";
			String batteryDrain = "";
			int line = 0;
			int i = 0, j, val = 0;
			int count = 0, flag = 0;
			double temp, max = 0.0, sum = 0.0;
			while ((readLine = br.readLine()) != null) {
				count += 1;
				StringTokenizer st = new StringTokenizer(readLine);
				while (st.hasMoreTokens()) {
					first = st.nextToken();
					if (flag == 0) {
						if (first.equals("Uid")) {
							batteryDrain = readLine.substring(16, 21);
							flag = 1;
						}
					}
					if (first.equals("Foreground")) {
						value = readLine.substring(27, 60);
						value = value.trim();
						break;
					}
				}
			}
			double drain = Double.parseDouble(batteryDrain);
			double percentage = drain/(double)1000;
			jsonobj.put("Foreground_time", value);
			jsonobj.put("Battery_percentage", percentage);
			jsonobj.put("Battery_drain", drain);
			JSONArray jsonList = new JSONArray();
		jsonList.add(jsonobj);
			try (FileWriter file1 = new FileWriter("F://BatteryOutput.json")) {
				file1.write(jsonList.toString());
				file1.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
