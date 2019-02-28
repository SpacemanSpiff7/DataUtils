package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Simone Longo on February 27, 2019
 * 
 * This utility takes 2 arguments:
 * (1) A file to convert: 
 * 		This file must be a text file with data entries separated by tabs.
 * 		The first column must be sorted
 * 		The rows and columns should not be named
 * (2) File name for the output file
 * 
 * What this does is insert a blank line in between unique values of the first column.
 * The purpose of this is to generate a file for use with the pm3d library of gnuplot.
 * The values should all be numerical values with 3 columns (an x, y, and z if you will) 
 * When plotting this data in gnuplot: "splot "output_file_name" w pm3d" the result is a 
 * 3D density plot.
 * 
 * This code is very basic with no exception handling so use at your own risk!
 * A default data set is provided if no arguments are provided as an example output.
 */
public class Converter {

	public static void main(String[] args) throws IOException {
		String filename, newfile;
		if (args.length == 0) {
			filename = "resources/opt.dat";
			newfile = "opt_space.dat";
		}
		else {
			filename = args[0];
			newfile = args[1];
		}
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(newfile));
		
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String nextline;
		double previous = -8.0;
		while ((nextline = reader.readLine()) != null) {
			double current;
			char[] line = nextline.toCharArray();
			int endIndex = returnEndIndex(line);

			if (line[0] == '-') {
				String num = "";
				for (int i = 1; i < endIndex; i++) {
					num += line[i]; // Account for '-' char
				}
				current = -1 * Double.parseDouble(num);
			}
			else {
				String num = "";
				for (int i = 0; i < endIndex; i++) {
					num += line[i]; 
				}
				current = Double.parseDouble(num);
			}
			
			
			if ((Math.abs(current - previous) > 0.000001)) writer.write("\n");
			
			writer.write(nextline + "\n");
			previous = current;
		}
		
		writer.flush();
		writer.close();
		reader.close();
	}

	private static int returnEndIndex(char[] line) {
		for (int i = 0; i < line.length; i++) {
			if (line[i] == '\t') return i;
		}
		return -1;
	}

}
