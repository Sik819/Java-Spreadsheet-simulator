package toBeCompleted;
import java.util.*;

public class Worksheet {

	private ArrayList<DataEntry> data;
	private String title;

	/**
	 * create a new worksheet with given title
	 * @param title
	 */
	public Worksheet(String title) {
		data = new ArrayList<DataEntry>();
		this.title = title;
	}

	/**
	 * @return a shallow copy of the data
	 */
	public ArrayList<DataEntry> getData() {
		return data;
	}

	/**
	 * 
	 * @return title of the worksheet
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * 
	 * @param row
	 * @param column
	 * @return value of item at given row and column (if any), null otherwise
	 */
	public Double get(int row, int column) {
		for(DataEntry item: data) {                                                         
			if(item.getRow()==row && item.getColumn()==column)
				return item.getValue();                                                                


		}			
		return null; 
	}

	/**
	 * set the value of DataEntry object at given row and column to given value
	 * 
	 * if a DataEntry object for given row and column already exists, overwrite the current value
	 * if a DataEntry object for given row and column doesn't exist, add a new DataEntry object
	 * with given row, column, value to the list.
	 * @param row
	 * @param column
	 * @param val
	 */
	public void set(int row, int column, double val) {	  	
		DataEntry cell = new DataEntry (row,column,val);
		if(this.get(row, column)!=null)
			data.set(this.indexOf(row, column),cell);
		else
			data.add(cell);
	}
	/**
	 * 
	 * @param row
	 * @param column
	 * @return index of DataEntry object in list data with given row and column
	 * return -1 if no such DataEntry object found
	 */
	public int indexOf(int row, int column) {
		for(int i = 0 ; i < data.size() ; i++) {
			if( data.get(i).getRow() == row && data.get(i).getColumn() == column )                     
				return i;
		}
		return -1;
	}

	/**
	 * 
	 * @param row1
	 * @param column1
	 * @param row2
	 * @param column2
	 * @return number of DataEntry objects in given address range , 0 if none
	 * ((row1, column1) to (row2, column2))
	 */
	public int count(int row1, int column1, int row2, int column2) {
		int count=0;
		for(DataEntry item: data) {
			if(item!=null && item.inRange(row1, column1, row2, column2)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 
	 * @param row1
	 * @param column1
	 * @param row2
	 * @param column2
	 * @return sum of values of all DataEntry objects in given address range 
	 * ((row1, column1) to (row2, column2))
	 * return 0 if there are no items in the given range
	 */
	public double total(int row1, int column1, int row2, int column2) {
		double total=0.0;
		for(DataEntry item: data) {
			if(item!=null && item.inRange(row1, column1, row2, column2)) {
				total+=item.getValue();
			}
		}
		return total;
	}

	/**
	 * 
	 * @param row1
	 * @param column1
	 * @param row2
	 * @param column2
	 * @return average of values of all DataEntry objects in given address range 
	 * ((row1, column1) to (row2, column2))
	 * return 0 if there are no items in the given range
	 */
	public double average(int row1, int column1, int row2, int column2) {
		ArrayList<Double> temp = new ArrayList();
		for(DataEntry item: data) {
			if(item!=null && item.inRange(row1, column1, row2, column2)) {
				temp.add(item.getValue());	    		 
			}
		}
		if(temp.size()==0)
			return 0;
		return this.total(row1, column1, row2, column2)/temp.size();
	}

	/**
	 * 
	 * @return value of the first row for which there is a DataEntry object
	 * in the list data
	 * return null if there is no data
	 */
	public Integer minRow() {
		if(data.isEmpty())
			return null;
		Integer minResult = data.get(0).getRow();
		for(int i = 0 ; i<data.size() ;i++) {
			if( data.get(i)!=null && data.get(i).getRow()<minResult) {
				minResult =  data.get(i).getRow();		
			}
		}
		return minResult;
	}

	/**
	 * 
	 * @return value of the last row for which there is a DataEntry object
	 * in the list data	 
	 * return null if there is no data
	 */
	public Integer maxRow() {
		if(data.isEmpty())
			return null;
		Integer maxResult = data.get(data.size()-1).getRow();
		for(int i = data.size()-1 ; i>=0 ;i--) {
			if( data.get(i)!=null && data.get(i).getRow()>maxResult) {
				maxResult =  data.get(i).getRow();		
			}
		}
		return maxResult;
	}

	/**
	 * 
	 * @return value of the first column for which there is a DataEntry object
	 * in the list data
	 * return null if there is no data
	 */
	public Integer minColumn() {
		if(data.isEmpty())
			return null;
		Integer minResult = data.get(0).getColumn();
		for(int i = 0 ; i<data.size() ;i++) {
			if( data.get(i)!=null && data.get(i).getColumn()<minResult) {
				minResult =  data.get(i).getColumn();		
			}
		}
		return minResult;
	}




	/**
	 * 
	 * @return value of the last column for which there is a DataEntry object
	 * in the list data
	 * return null if there is no data
	 */
	public Integer maxColumn() {
		if(data.isEmpty())
			return null;
		Integer maxResult = data.get(data.size()-1).getColumn();
		for(int i = data.size()-1 ; i>=0 ;i--) {
			if( data.get(i)!=null && data.get(i).getColumn()>maxResult) {
				maxResult =  data.get(i).getColumn();		
			}
		}
		return maxResult;
	}

	/**
	 * 
	 * @param row
	 * @return list of values of data items from given row
	 * return empty list if no items belong to the given row 
	 */
	public ArrayList<Double> getDataByRow(int row) {
		ArrayList<Double> list = new ArrayList();
		for(DataEntry item:data) {
			if( item.getRow()==row) 
				list.add(item.getValue());
		}					
		return list;
	}

	/**
	 * 
	 * @param column
	 * @return list of values of data items from given column
	 * return empty list if no items belong to the given column 
	 */
	public ArrayList<Double> getDataByColumn(int column) {
		ArrayList<Double> list = new ArrayList();
		for(DataEntry item:data) {
			if( item.getColumn()==column) 
				list.add(item.getValue());
		}					
		return list;
	}

	/**
	 * replace all occurrences of key item by replacement item
	 * @param key
	 * @param replacement
	 */
	public void replace(double key, double replacement) {
		for(DataEntry item : data ) {
			if(item.getValue()==key)
				item.setValue(replacement);	    	
		}
	}

	/**
	 * 
	 * @param key
	 * @return a list of DataEntry objects that have 
	 * key as their value attribute
	 */
	public ArrayList<DataEntry> find(double key) {
		ArrayList<DataEntry> list =  new ArrayList();
		for(DataEntry item : data ) {
			if(item.getValue()==key)
				list.add(item);	    	
		}
		return list;
	}

	/**
	 * return String output of the object in format required
	 * in specifications
	 * 
	 * NOTE there is no test for this method. output should resemble the sample output provided 
	 * in the specifications
	 */
	public String toString() {
		String result = new String();
		//inserting title
		result = title + "\n";

		//labeling columns
		String labelColumn = new String();
		for(int c = 0 ; c <= maxColumn()  ; c++) {		
			labelColumn += "\t" + letter(c) + "("    + c + ")";
		}
		result += labelColumn ;

		//Inserting rows and values
		String labelRow = new String();
		String labelValue = new String();				
		for(int r = 0 ; r<=maxRow() ; r++) {
			labelValue = "";
			for(int c=0 ; c<=maxColumn() ; c++) {			
				if(get(r,c)==null)
					labelValue += "-" + "\t";
				else
					labelValue += get(r,c) + "\t";
			}
			labelRow += "\n" + r + "\t" + labelValue ;		
		}
		result += labelRow + "\n";

		// inserting dashed lines after each sheet
		for(int i = 0 ; i<15 ; i++)
			result += "-";

		//woohoo
		return result + "\n";
	}
	public String letter ( int a ) {     //a recursive method that returns the needed letter from the alphabet 
		int quotient = a/26;             //input the index of letter from a character array of alphabets (char[] =  'A' to 'Z')
		int remainder = a%26;	 
		if(a<26)                         //terminate if parameter is less than the number of letters in the alphabet
			return Character.toString((char)(65+remainder));           //ascii code used to get letter

		else
			return Character.toString((char)(64+quotient)) + letter (remainder);							
	}



}




