package modelo;

import java.io.IOException;

public class LineaIncorrectaException extends Exception {

		String linea;
		
		public LineaIncorrectaException(String linea) {
			this.linea=linea;
		}
		
		public String getLinea() {
			return this.linea;
		}
		
		@Override
		public String getMessage() {
			return "ERROR! Línea de archivo con formato incorrecto: "+this.linea;
		}
}
