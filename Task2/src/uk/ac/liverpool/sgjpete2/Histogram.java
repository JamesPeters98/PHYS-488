package uk.ac.liverpool.sgjpete2;

import java.io.*;

class Histogram
{	
    private double binlow, binhigh;
    private double binwidth;
    private int nbins;
    private double[] binCentre;
    private String histname;
    
    private int underflows = 0;
    private int overflows = 0;
    private int fills = 0;

    // double array to store the actual histogram data
    private double[] sumWeights;

    // constructor for the class Histogram
    public Histogram(int numberOfBins, double start, double end, String name)
    {
	// store the parameters and setup the histogram
	// note that parameters need to have different names than class variables
	nbins = numberOfBins;
	binlow = start;
	binhigh = end;
	histname = name;

	binwidth = (binhigh - binlow) / (double) nbins;
	sumWeights = new double[nbins];
	
	// calculate and save the x coordinate of the centre of each bin
	binCentre = new double[nbins];
	for (int i = 0; i < nbins; i++) {
	    binCentre[i] = binlow + (i+0.5)*binwidth;
	}
    }

    public int getNbins()
    {
	return nbins;
    }

    public void fill(double value){
    	// fid correct bin and add 1.
    	int ibin = (int) ( (value - binlow)/binwidth);
	
		if((value >= binlow)&&(value <= binhigh)){
			sumWeights[ibin] = sumWeights[ibin] + 1.0;
			fills++;
		} 
		else if(value < binlow){ underflows++; }
		else if(value > binhigh){ overflows++; }
    }

    public double getContent(int nbin) {
		// returns the contents on bin 'nbin' to the user
		return sumWeights[nbin];
    }
    
    public double getError(int nbin){
    	//Returns statistical error on  particular value.
    	return Math.sqrt(sumWeights[nbin]);
    }

    public void print() {
		System.out.println("Histogram " + histname);
		
		for (int bin = 0; bin < getNbins(); bin++) {
		    System.out.println("Bin " + bin + " = " +getContent(bin)+" +/- "+getError(bin));
		}
		
		System.out.println("Overflows: "+overflows);
		System.out.println("Underflows: "+underflows);
		System.out.println("Total fills: "+fills);
    }
    
    //-------------------------------------
    public void writeToDisk(String filename) throws IOException
    {
        FileWriter file = new FileWriter(filename);     // this creates the file with the given name
        PrintWriter outputFile = new PrintWriter(file); // this sends the output to file1
        

        // Write the file as a comma seperated file (.csv) so it can be read it into EXCEL
        // first some general information about the histogram
        outputFile.println("histname, " + histname);
        outputFile.println("binlow, " + binlow);
        outputFile.println("binwidth, " + binwidth);
        outputFile.println("nbins, " + nbins);

        // now make a loop to write the contents of each bin to disk, one number at a time
        // together with the x-coordinate of the centre of each bin.
        for (int n = 0; n < nbins; n++) {
            // comma separated values
            outputFile.println(n + "," + binCentre[n] + "," + getContent(n) + "," + getError(n));
        }
        outputFile.close(); // close the output file
        return;
    }
}
