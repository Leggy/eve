package yml.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

import com.esotericsoftware.yamlbeans.*;

public class YamlTester {
	
	
	public static void main(String args[]) throws FileNotFoundException, YamlException{
		
		YamlReader reader = new YamlReader(new FileReader("test2.yml"));
	    Object object = reader.read();
	    System.out.println(object);
	    Map map = (Map)object;
	    System.out.println(map.get("name"));
	    System.out.println(map.get("requirements"));
//	    System.out.println(map.get("phone numbers"));
//	    List list = (List)map.get("phone numbers");
//	    for(Object entry: list){
//	    	System.out.println(entry);
//	    	
//	    }
	    
	    
	    Object object2 = reader.read();
	    System.out.println(object);
	    Map map2 = (Map)object;
	    System.out.println(map.get("name"));
	    System.out.println(map.get("requirements"));
	}

}
