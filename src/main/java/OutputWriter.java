

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OutputWriter {
	String fileName;
	BufferedWriter ow;

	public int flushInterval = 10000;
	int flushCounter ;

	public OutputWriter(String fileName) {
		this.fileName = fileName;
		try {
			ow = new BufferedWriter(new FileWriter(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeOutput(String str) {
		try {
			ow.write(str+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void flush() {
		try{
			ow.flush();
		}catch(Exception ex) {
			
		}
	}
	
	public void close() {
		try {
			ow.flush();
			ow.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
