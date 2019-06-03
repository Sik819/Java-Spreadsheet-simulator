public class DataEntry {	
	private int row, column;
	private double value;

	/**
	 * assign values to instance variables using setters
	 */
	public DataEntry(int r, int c, double val) {
		setRow(r);
		setColumn(c);
		value = val;
	}

	/**
	 * assign the higher of 0 and r to row
	 * @param r
	 */
	public void setRow(int r) {
		row = Math.max(0, r);
	}

	/**
	 * assign the higher of 0 and c to column
	 * @param c
	 */
	public void setColumn(int c) {
		column = Math.max(0, c);
	}

	public void setValue(double val) {
		value = val;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public double getValue() {
		return value;
	}


	/**
	 * 
	 * @param row1
	 * @param column1
	 * @param row2
	 * @param column2
	 * @return true if the current item is in the range provided
	 * i.e., between rows row1 and row 2 (inclusive) and between 
	 * columns column1 and column2 (inclusive), false otherwise
	 */
	public boolean inRange(int row1, int column1, int row2, int column2) {
		return(((row1>= row && row2<=row) || (row2>=row && row1<=row))                         //checking if row in range
				                          && 
			((column1>=column && column2<=column ) || (column1<=column && column2>=column)));  //checking if column in range
	}
}
