import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Matching
{
	public static HashTableForMatching<Item, Point> hashtable;
	public static ArrayList<String> data;
	public static final Pattern INSERT_DATA_COMMAND_PATTERN = Pattern.compile("< (.*)");
	public static final Pattern PRINT_DATA_COMMAND_PATTERN = Pattern.compile("@ ([0-9]*)");
	public static final Pattern SEARCH_COMMAND_PATTERN = Pattern.compile("\\? (.*)");
	
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("QUIT") == 0)
					break;
				
				command(input);
			} catch (FileNotFoundException e){
				System.out.println("파일이 발견되지 않았습니다. 오류  : " + e.toString());
			} catch (IOException e)
			{
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	private static void command(String input) throws FileNotFoundException, IOException
	{
		if (input.length() == 0) return;
		Matcher insertData = INSERT_DATA_COMMAND_PATTERN.matcher(input);
		if (insertData.matches()){
			hashtable = new HashTableForMatching<Item, Point>();
			data = new ArrayList<String>();
			String fileDirectory = insertData.group(1);
			try (BufferedReader br = new BufferedReader(new FileReader(fileDirectory))) {
				String line;
				for (int i = 0; (line = br.readLine()) != null; i++) {
					data.add(line);
					for (int j = 0; j <= line.length() - 6; j++){
						String substring = line.substring(j, j + 6);
						hashtable.put(new Item(substring), new Point(i + 1, j + 1));
					}
				}
			}
			return;
		}
		if (hashtable == null) return;
		Matcher printData = PRINT_DATA_COMMAND_PATTERN.matcher(input);
		if (printData.matches()){
			Integer index = Integer.parseInt(printData.group(1));
			System.out.println(hashtable.indexOf(index));
			return;
		}
		Matcher search = SEARCH_COMMAND_PATTERN.matcher(input);
		if (search.matches()){
			String keyword = search.group(1);
			MyLinkedList<Point> itemList = hashtable.getItems(new Item(keyword.substring(0, 6)));
			MyLinkedList<Point> resultList = new MyLinkedList<Point>();
			if (itemList == null){
				resultList.add(new Point(0, 0));
			} else {
				for (LinkedListNode<Point> item = itemList.getHead(); item != null; item = item.getNextNode()){
					String line = data.get(item.getValue().getX() - 1);
					if (line.substring(item.getValue().getY() - 1, line.length()).indexOf(keyword) == 0){
						resultList.add(item.getValue());
					}
				}
			}
			System.out.println(resultList);
			return;
		}
		System.out.println("지원되지 않는 명령어 인 듯..?");
	}
}