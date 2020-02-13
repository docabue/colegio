package colegio;
//
import java.util.Iterator;

import javax.swing.JOptionPane;

import com.matisse.MtDatabase;
import com.matisse.MtException;
import com.matisse.MtObjectIterator;
import com.matisse.MtPackageObjectFactory;

public class crud {

	static String hostname="localhost";
	static String dbname="colegio";
	
	
	public static void main(String[] args) {
       boolean finalizar=false;
       int opcion=0;
       do
       {
    	   opcion=Integer.parseInt(JOptionPane.showInputDialog("introduce una opcion: " +"\n"+ "1º Crear alumno "+ "\n" + "2º Eliminar alumno "+ "\n"+ "3º Modificar alumno " + "\n" + "4º Consultar alumno " +"\n"+"5º Salir"));
    	   switch(opcion)
    	   {
    	   case 1:
    	   {
    		   String nombrec=JOptionPane.showInputDialog("Introduce el nombre del alumno que desa crear: ");
    			String apellidosc=JOptionPane.showInputDialog("Introduce los apellidos del alumno que desea crear: ");

    			creaAlumno(nombrec,apellidosc);
    		   break;
    	   }
    	   case 2:
    	   {
    		   String nombre=JOptionPane.showInputDialog("Introduce el nombre del alumno que desa borrar: ");
    			
    			borrarAlumno(nombre);
    		   
    		   break;
    	   }
    	   case 3:
    	   {
    		   String nombre=JOptionPane.showInputDialog("Introduce el nombre del alumno que desa modificar : ");
    			String apellidos=JOptionPane.showInputDialog("Introduce los apellidos nuevos: ");
    			modificaAlumno(nombre, apellidos);
    		   
    		   break;
    	   }
    	   case 4:
    	   {
    		   String nombreConsulta= JOptionPane.showInputDialog("Introduce el nombre del alumno que desea consultar: " );
    	         leeAlumno(nombreConsulta);

    		   
    		   break;
    	   }
    	   case 5:
    	   {
    		   finalizar=true;
    	   }
    		   default:JOptionPane.showMessageDialog(null, "Esa opcion no existe");
    	   
    	   }
    	   
    	   
       }
      while(!finalizar);
		/*
		String nombrec=JOptionPane.showInputDialog("Introduce el nombre del alumno que desa crear: ");
		String apellidosc=JOptionPane.showInputDialog("Introduce los apellidos del alumno que desea crear: ");

		creaAlumno(nombrec,apellidosc);
		*/
		
		/*
		String nombre=JOptionPane.showInputDialog("Introduce el nombre del alumno que desa borrar: ");
		String apellidos=JOptionPane.showInputDialog("Introduce los apellidos del alumno que desea borrar: ");
		borrarAlumno(nombre, apellidos);
       */
		
		
		/*
		String nombre=JOptionPane.showInputDialog("Introduce el nombre del alumno que desa modificar : ");
		String apellidos=JOptionPane.showInputDialog("Introduce los apellidos nuevos: ");
		modificaAlumno(nombre, apellidos);
        
*/

/*
         String nombreConsulta= JOptionPane.showInputDialog("Introduce el nombre del alumno que desea consultar: " );
         leeAlumno(nombreConsulta);

     */  


	}

//crea alumno


	public static void creaAlumno(String nombre, String apellidos) {
		try {
		// Abre la base de datos con el Hostname (localhost),
		//dbname (Primera) y el namespace "Primera".
		MtDatabase db = new MtDatabase(hostname, dbname, new
		MtPackageObjectFactory("", " Colegio"));
		db.open();
		db.startTransaction();
		System.out.println("Conectado a la base de datos " +
		db.toString() + " de Matisse");
		// Crea un objeto Autor
		alumno a1 = new alumno(db);
		a1.setNombre(nombre);
		a1.setApellidos(apellidos);
		System.out.println("Alumno añadido");
		// Ejecuta un commit para materializar las peticiones.
				db.commit();
				// Cierra la base de datos.
				db.close();



		} catch (MtException mte) {
		System.out.println("MtException : " + mte.getMessage());
		}
	}

	
	//elimina alumno
	
	
	public static void borrarAlumno(String nombre) {
		
		try {
			
         int numeroAlumno=0;
		MtDatabase db = new MtDatabase(hostname, dbname, new
		MtPackageObjectFactory("", "colegio"));
		

		db.open();
		db.startTransaction();
	
	     numeroAlumno = (int) alumno.getInstanceNumber(db);

		MtObjectIterator<alumno> iter = alumno.<alumno>instanceIterator(db);
		System.out.println("Conectado a la base de datos " +
				db.toString() + " de Matisse");
		
		
			while (iter.hasNext()) {
				alumno[] tAlumno = iter.next(numeroAlumno);
				for (int i = 0; i < tAlumno.length; i++) {
					// Busca una autor con nombre 'nombre'
					if (tAlumno[i].getNombre().compareTo(nombre)== 0) {
					tAlumno[i].remove();
					System.out.println("alumno borrado");
					} 
					
				}
				
				}
					iter.close();
		
			
		
		
		// materializa los cambios y cierra la BD
		db.commit();
		db.close();
		
		} 
		catch (MtException mte) {
		System.out.println("MtException : " + mte.getMessage());
		}
		
	}
	
	
	
	
	
	public static void modificaAlumno( String nombre, String apellido) {
		try {
        int numeroAlumno=0;
		MtDatabase db = new MtDatabase(hostname, dbname, new
				MtPackageObjectFactory("", "colegio"));
				

				db.open();
				db.startTransaction();
			
			
			numeroAlumno = (int) alumno.getInstanceNumber(db);
			// Crea un Iterador (propio de Java)
			MtObjectIterator<alumno> iter = alumno.<alumno>instanceIterator(db);
		
			while (iter.hasNext()) {
			alumno[] tAlumno = iter.next(numeroAlumno);
			for (int i = 0; i < tAlumno.length; i++) {
				// Busca una autor con nombre 'nombre'
				if (tAlumno[i].getNombre().compareTo(nombre)== 0) {
				tAlumno[i].setApellidos(apellido);
				System.out.println("El apellido se ha cambiado");
				} 
				
			}
			
			}
				iter.close();
				// materializa los cambios y cierra la BD
				db.commit();
				db.close();
				
	}
	             catch (MtException mte) {
				System.out.println("MtException : " + mte.getMessage());
				}
       }
	
	
	
	
	
	
	
	
	
	
	public static void leeAlumno( String nombre) {
		try {
			String consulta="";
        int numeroAlumno=0;
		MtDatabase db = new MtDatabase(hostname, dbname, new
				MtPackageObjectFactory("", "colegio"));
				

				db.open();
				db.startTransaction();
			
			
			numeroAlumno = (int) alumno.getInstanceNumber(db);
			// Crea un Iterador (propio de Java)
			MtObjectIterator<alumno> iter = alumno.<alumno>instanceIterator(db);
		
			while (iter.hasNext()) {
			alumno[] tAlumno = iter.next(numeroAlumno);
			for (int i = 0; i < tAlumno.length; i++) {
				// Busca una autor con nombre 'nombre'
				if (tAlumno[i].getNombre().compareTo(nombre)== 0) {
				
				System.out.println("El alumno " + nombre + "tiene el apellido: "+  tAlumno[i].getApellidos() );
				} 
				
			}
			
			}
				iter.close();
				// materializa los cambios y cierra la BD
				db.commit();
				db.close();
				
	}
	             catch (MtException mte) {
				System.out.println("MtException : " + mte.getMessage());
				}
       }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

			
	
	
	
	
	
	
	
	
	
	
	
	
	
	



