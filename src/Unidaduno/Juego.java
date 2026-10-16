package Unidaduno;


/*public class Juego {

    Scanner teclado;
    Persona persona;
    Perro perro;
    Gato michi;

    Juego() {
        this.teclado = new Scanner(System.in);
        this.persona = new Persona();
        this.perro = new Perro;
        michi = new Gato;
        this.menu();
    }

    public void menu() {
        do {
            System.out.println("\nWelcome to Juego..."
                    + "\n0. Salir"
                    + "\n1. Gestor de Personas."
                    + "\n2. Gestor de perros."
                    + "\nSelecciona opcion: ");
            switch (this.teclado.nextInt()) {
                case 0 ->
                    System.exit(0);
                case 1 ->
                    this.persona.menu();
                case 2 ->
                    this.perro.menu();
                default ->
                    System.out.println("Opcion no valida.");
            }
        } while (true);
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}

/*Perro firulais, pancho;
        Persona juan, lisa;

        firulais = new Perro("Firulais", 10);
        pancho = new Perro("Pancho", 10);

        juan = new Persona("Juan", 7);
        lisa = new Persona("Lisa", 8);

        firulais.getNombre();
        firulais.setVelocidad(35);
        
        pancho.getNombre();
        pancho.getVelocidad();

        juan.getNombre();
        juan.gritar(firulais);
        firulais.morder(juan);
        
        
        lisa.getNombre();
    }
        }  */
