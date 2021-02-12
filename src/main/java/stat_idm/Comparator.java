package stat_idm;

import java.util.Iterator;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

/**
 * Comparateur récursif sur les arbres json
 * @author Maud Garçon & Emmanuel Chauvel
 *
 */

public class Comparator {
	
	private float total = 0;
	private float similitude = 0;
	
	public float compare(JsonNode n1, JsonNode n2) {
		compareRecursive(n1, n2);
		
		return similitude/total;
	}
	
	public void compareRecursive(JsonNode n1, JsonNode n2) {
		total++;
		if (n1.equals(n2)) {
			similitude++;
			if (n1.getNodeType() == JsonNodeType.OBJECT) {
				for (Iterator it = n1.fields(); it.hasNext();) {
					Map.Entry<String, JsonNode> child1 = (Map.Entry<String, JsonNode>) it.next();
					compareRecursive(child1.getValue(), n2.get(child1.getKey()));
				}
			}
			else if (n1.getNodeType() == JsonNodeType.ARRAY) {
				for (int i = 0; i < n1.size(); i++) {
					compareRecursive(n1.get(i), n2.get(i));
				}
			}
		}
		else {
			if (n1.getNodeType() == JsonNodeType.OBJECT) {
				for (Iterator it = n1.fields(); it.hasNext();) {
					Map.Entry<String, JsonNode> child1 = (Map.Entry<String, JsonNode>) it.next();
					JsonNode child2 = n2.get(child1.getKey());
					if (child2 != null)
						compareRecursive(child1.getValue(), child2);
				}
			}
			else if (n1.getNodeType() == JsonNodeType.ARRAY && n2.getNodeType() == JsonNodeType.ARRAY) {
				for (int i = 0; i < Math.min(n1.size(), n2.size()); i++) {
					compareRecursive(n1.get(i), n2.get(i));
				}
			}
		}
	}

}
