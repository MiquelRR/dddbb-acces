package clases;

public class Aeropuerto {
        private String codigo;
        private String nombre;
        private String ciudad;
        private String pais;
        public Aeropuerto(String codigo, String nombre, String ciudad, String pais) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.ciudad = ciudad;
            this.pais = pais;
        }
        @Override
        public String toString() {
            return "[" + codigo + "], " + ciudad;
        }
        public String getCodigo() {
            return codigo;
        }
        public String getNombre() {
            return nombre;
        }
        public String getCiudad() {
            return ciudad;
        }
        public String getPais() {
            return pais;
        }
}

