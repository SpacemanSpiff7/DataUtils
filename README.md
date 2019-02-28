# DataUtils
Created by Simone Longo on February 27, 2019
 
 This utility takes 2 arguments:
 (1) A file to convert: 
 		This file must be a text file with data entries separated by tabs.
 		The first column must be sorted
 		The rows and columns should not be named
 (2) File name for the output file
 
 What this does is insert a blank line in between unique values of the first column.
 The purpose of this is to generate a file for use with the pm3d library of gnuplot.
 The values should all be numerical values with 3 columns (an x, y, and z if you will) 
 When plotting this data in gnuplot: "splot "output_file_name" w pm3d" the result is a 
 3D density plot.
 
 This code is very basic with no exception handling so use at your own risk!
 * A default data set is provided if no arguments are provided as an example output.
