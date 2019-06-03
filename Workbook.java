package toBeCompleted;
import java.util.*;

public class Workbook {
	private String title;
	private ArrayList<Worksheet> sheets;

	public Workbook(String title) {
		this.title = title;
		sheets = new ArrayList<Worksheet>();
	}

	public void add(Worksheet sheet) {
		sheets.add(sheet);
	}

	public Worksheet get(int index) {
		if(index >= 0 && index < sheets.size())
			return sheets.get(index);
		else
			return null;
	}

	public String toString() {
		String result = title+"\n\n";
		if(sheets.size()==0)
			return title+": empty";
		for(Worksheet sheet: sheets)
			result = result + sheet.toString()+"\n";
		return result;
	}

	/**
	 * replace all occurrences of key item by replacement item
	 * across all worksheets
	 * @param key
	 * @param replacement
	 */
	public void replace(double key, double replacement) {
		for(Worksheet sheet: sheets) 
			sheet.replace(key, replacement);
	}

	/**
	 * create a deep copy of worksheet at index idx (if any)
	 * with the given title
	 * @param idx
	 * @param title
	 */
	public void duplicate(int idx, String title) {
		if(idx < 0 || idx >= sheets.size())
			return;
		Worksheet sheet = new Worksheet(title);
		ArrayList<DataEntry> data = sheets.get(idx).getData();
		for(DataEntry item: data) {
			int r = item.getRow();
			int c = item.getColumn();
			double val = item.getValue();
			sheet.set(r, c, val);
		}
		sheets.add(sheet);
	}

	/**
	 * set value of cell at address (r, c) in sheet
	 * at index idx (if any) to val
	 * @param idx
	 * @param r
	 * @param c
	 * @param val
	 */
	public void set(int idx, int r, int c, double val) {
		if(idx < 0 || idx >= sheets.size())
			return;
		Worksheet sheet = sheets.get(idx);
		sheet.set(r, c, val);
	}

	public Double total(int idx, int row1, int column1, int row2, int column2) {
		if(idx < 0 || idx >= sheets.size())
			return null;
		return sheets.get(idx).total(row1, column1, row2, column2);
	}

	public Double average(int idx, int row1, int column1, int row2, int column2) {
		if(idx < 0 || idx >= sheets.size())
			return null;
		return sheets.get(idx).average(row1, column1, row2, column2);
	}

	public int count(int idx, int row1, int column1, int row2, int column2) {
		if(idx < 0 || idx >= sheets.size())
			return 0;
		return sheets.get(idx).count(row1, column1, row2, column2);
	}

	/**
	 * 
	 * copy cell range from sheet at sourceIndex (if any) from
	 * startRowSource, startColumnSource to endRowSource, endColumnSource
	 * and paste in sheet at destIndex (if any) starting at
	 * cell address startRowDest, startColumnDest
	 * 
	 * @param sourceIndex
	 * @param startRowSource
	 * @param startColumnSource
	 * @param endRowSource
	 * @param endColumnSource
	 * @param destIndex
	 * @param startRowDest
	 * @param startColumnDest
	 */
	public void copyPaste(int sourceIndex, int startRowSource, int startColumnSource, int endRowSource, int endColumnSource,
			int destIndex, int startRowDest, int startColumnDest) {
		if(sheets.isEmpty())
			return;     
		Worksheet SourceSheet= sheets.get(sourceIndex);
		Worksheet TargetSheet= sheets.get(destIndex);

		for( DataEntry item: SourceSheet.getData()) {
			if( item.inRange(startRowSource, startColumnSource, endRowSource, endColumnSource)) { 
				int rd =  item.getRow() - Math.min(startRowSource, endRowSource) , cd =   item.getColumn() - Math.min(startColumnSource, endColumnSource) ;  // get absolute and relative rows and columns

				TargetSheet.set(startRowDest + rd,startColumnDest+ cd, item.getValue());

			}


		}


	}
}


