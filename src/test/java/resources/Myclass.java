package resources;

public class Myclass {

	enum enumDemo {
		HIGH(3), // calls constructor with value 3
		MEDIUM(2), // calls constructor with value 2
		LOW(1) // calls constructor with value 1
		; // semicolon needed when fields / methods follow

		private final int levelCode;

		enumDemo(int levelCode) {
			this.levelCode = levelCode;
		}

		public int getLevelCode() {
			return this.levelCode;
		}

	}
	
	public static void main(String[] args) {
		enumDemo myVar = enumDemo.MEDIUM; 
	    System.out.println(myVar); // выводит значение Медиума
	    
	    enumDemo myVar2=enumDemo.valueOf("LOW");	 //создаем "объект"-enum instance (each constant), чтобы потом иметь доступ к функциям enum класса    
	    System.out.println( myVar2.getLevelCode());
	    
	    for (enumDemo level : enumDemo.values()) {
	        System.out.println(level);
	    }
	  }

}