import java.util.HashMap;
import java.util.Map;

public class Potion {
	
	private String type;
	private int restore;
	private Map<String, Integer> potionHeal;

	public Potion(String type) {
		this.type = type;
		this.potionHeal = new HashMap();
		potionHeal.put("Potion", 20);
		potionHeal.put("SuperPotion", 60);
		potionHeal.put("HyperPotion", 120);
		potionHeal.put("MaxPotion", 50000);
	}
	
	public void setPotion() {
		restore = potionHeal.get(type);
	
	}
	
	public int healAmount() {
		return restore;
	}

}
