
public class Item implements Comparable<Item> {
	String value;
	
	Item(String value){
		this.value = value;
	}

	@Override
	public int hashCode() {
		int result = 0;
		for (int i = 0; i < value.length(); i++){
			result += (int)value.charAt(i);
		}
		return result % 100;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public int compareTo(Item o) { 
		return this.value.compareTo(o.value);
	}
	
	@Override
	public String toString(){
		return value.toString();
	}
}
