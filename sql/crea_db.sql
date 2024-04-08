CREATE DATABASE IF NOT EXISTS Reservas;

USE Reservas;

CREATE TABLE IF NOT EXISTS Pasajeros (
    numero_pasaporte VARCHAR(20) PRIMARY KEY,
    nombre_pasajero VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Aeropuertos (
    Codigo VARCHAR(5) PRIMARY KEY,
    Nombre VARCHAR(30),
    Pais VARCHAR(25),
    Ciudad VARCHAR(25)
);
CREATE TABLE IF NOT EXISTS Tipo_de_Avion (
    Id_tipo INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(100),
    asientos INT
);
CREATE TABLE IF NOT EXISTS Vuelos (
    id_vuelo INT AUTO_INCREMENT PRIMARY KEY,
    origen VARCHAR(5),
    destino VARCHAR(5),
    fecha DATE,
    FOREIGN KEY (origen) REFERENCES Aeropuertos(Codigo),
    FOREIGN KEY (destino) REFERENCES Aeropuertos(Codigo),
    CONSTRAINT chk_origen_destino CHECK (origen <> destino)
);
CREATE TABLE IF NOT EXISTS Plazas (
    Id_plaza INT AUTO_INCREMENT PRIMARY KEY,
    id_asiento VARCHAR(5),
    id_pasajero VARCHAR(20),
    id_vuelo INT,
    ocupado ENUM('si', 'no') DEFAULT 'no',
    FOREIGN KEY (id_vuelo) REFERENCES Vuelos(id_vuelo),
    FOREIGN KEY (id_pasajero) REFERENCES Pasajeros(numero_pasaporte)
);

insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("TXL", "Tegel", "Alemania", "Berlín");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("THF", "Tempelhof", "Alemania", "Berlín");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SXF", "Schonefeld", "Alemania", "Berlín");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CGN", "Colonia", "Alemania", "Colonia");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("DUS", "Dusseldorf", "Alemania", "Dusseldorf");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("FRA", "Frankfurt Int.", "Alemania", "Frankfurt");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("HAM", "Fuhlsbuettel", "Alemania", "Hamburgo");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("MUC", "Franz Strauss", "Alemania", "Munich");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("STR", "Echterdingen", "Alemania", "Stuttgart");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("LAD", "4 de Fevereiro", "Angola", "Luanda");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CUR", "Apto. Hato", "Antillas H.", "Curacao");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("ANU", "V. C. Bird Int.", "Antigua", "Antigua");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("ALG", "Houari-Boumedienne", "Argelia", "Argel");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BUE", "Ezeiza", "Argentina", "Buenos Aires");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("AUA", "Reina Beatrix", "Aruba", "Aruba");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("MEL", "Tullamarine", "Australia", "Melbourne");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SYD", "Kingsford Smith", "Australia", "Sydney");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("GRZ", "Thalerhof", "Austria", "Graz");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("VIE", "Viena Int.", "Austria", "Viena");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("NAS", "International", "Bahamas", "Nassau");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("PID", "Paradise Island", "Bahamas", "Nassau");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BGI", "G. Adams Int.", "Barbados", "Barbados");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BRU", "National", "Bélgica", "Bruselas");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CBB", "Wilsterman", "Bolivia", "Cochabamba");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("LPB", "El Alto", "Bolivia", "La Paz");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("VVI", "Viru Viru Int.", "Bolivia", "Sta. Cruz de la Sierra");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SJJ", "Butmir", "Bosnia", "Sarajevo");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BEL", "Val de Cans", "Brasil", "Belem");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CNF", "Tancredo Neves", "Brasil", "Belo Horizonte");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("PLU", "Pampulha", "Brasil", "Belo Horizonte");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BSB", "International", "Brasil", "Brasilia");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CPQ", "International", "Brasil", "Campinas");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CWB", "Alfonso Pena", "Brasil", "Curitiba");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("FLN", "Hercilio Luz", "Brasil", "Florianópolis");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("FOR", "Pinto Martins", "Brasil", "Fortaleza");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("IGU", "Cataratas", "Brasil", "Foz de Iguazú");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("IOS", "Eduardo Gomes", "Brasil", "Ilheus");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("MCZ", "Palmares", "Brasil", "Maceió");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("MAO", "Eduardo Gómes Int.", "Brasil", "Manaos");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("POA", "Salgado Filho", "Brasil", "Porto Alegre");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("PVH", "Belmonte", "Brasil", "Porto Velho");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("REC", "Guararapes Int.", "Brasil", "Recife");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("GIG", "International", "Brasil", "Río de Janeiro");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SDU", "Santos Dumont", "Brasil", "Río de Janeiro");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SSA", "Luis E. Magalhaes", "Brasil", "Salvador");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SLZ", "Mal Cunha Macha", "Brasil", "Sao Luiz");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CGH", "Congonhas", "Brasil", "Sao Paulo");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("GRU", "Guarulhos", "Brasil", "Sao Paulo");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("VCP", "Viracopos", "Brasil", "Sao Paulo");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("TBT", "International", "Brasil", "Tabatinga");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SOF", "Vrazhdebna", "Bulgaria", "Sofía");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("YUL", "Dorval", "Canadá", "Montreal");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("YMX", "Mirabel Int.", "Canadá", "Montreal");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("YOW", "International", "Canadá", "Ottawa");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("YYZ", "Pearson Int.", "Canadá", "Toronto");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("YKZ", "Buttonville", "Canadá", "Toronto");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("YVR", "International", "Canadá", "Vancouver");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SVO", "Sheremetyevo", "CEI", "Moscú");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("DME", "Domodedovo", "CEI", "Moscú");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("VKO", "Vnukovo", "CEI", "Moscú");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("ANF", "Cerro Moreno", "Chile", "Antofagasta");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("ARI", "Chacalluta", "Chile", "Arica");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("IQQ", "Diego Aracena", "Chile", "Iquique");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("PMC", "Tepual", "Chile", "Puerto Montt");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("PUQ", "Presidente Ibáñez", "Chile", "Punta Arenas");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SCL", "A. M. Benítez", "Chile", "Santiago de Chile");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BJS", "Beijing", "China", "Beijing");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SHA", "Hongqiao", "China", "Shanghai");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BAQ", "E. Cortissoz", "Colombia", "Barranquilla");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BOG", "El Dorado", "Colombia", "Bogotá");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CLO", "A. Bonilla Aragón", "Colombia", "Cali");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CTG", "Rafael Nuñez Int.", "Colombia", "Cartagena");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("MDE", "José María Cordova", "Colombia", "Medellín");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BZV", "Maya Maya", "Congo", "Brazzaville");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SEL", "Incheon Internacional", "Corea", "Seúl");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SJO", "Juan Santamaría Int.", "Costa Rica", "San José");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("HAV", "José Martí Int.", "Cuba", "Habana");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("VRA", "Juan Gualberto Gómez", "Cuba", "Varadero");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CPH", "Copenhague", "Dinamarca", "Copenhague");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("GYE", "Simón Bolívar", "Ecuador", "Guayaquil");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("UIO", "Mariscal Sucre", "Ecuador", "Quito");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CAI", "International", "Egipto", "El Cairo");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SAL", "Comalapa Int.", "El Salvador", "San Salvador");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("GLA", "Glasgow", "Escocia", "Glasgow");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("PIK", "Prestwick", "Escocia", "Glasgow");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BTS", "Ivanka", "Eslovaquia", "Bratislava");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("LJU", "Brnik", "Eslovenia", "Ljulbjand");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("ALI", "El Altet", "España", "Alicante");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BCN", "El Prat", "España", "Barcelona");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BIO", "Sondica", "España", "Bilbao");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("IBZ", "Ibiza", "España", "Ibiza");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("ACE", "Lanzarote", "España", "Lanzarote");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("LPA", "Gando", "España", "Las Palmas");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("MAD", "Barajas", "España", "Madrid");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("AGP", "Pablo Picasso", "España", "Málaga");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("PMI", "Son San Juan", "España", "Palma de Mallorca");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SCQ", "La Bacolla", "España", "Stgo. de Compostela");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("TFN", "Los Rodeos", "España", "Tenerife");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("TFS", "Reina Sofía", "España", "Tenerife");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("VLC", "Manises", "España", "Valencia");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("VGO", "Peinador", "España", "Vigo");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("MNL", "Ninoy Aquino Int.", "Filipinas", "Manila");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("HEL", "Helsinki-Vantaa", "Finlandia", "Helsinki");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("LYS", "Satolas", "Francia", "Lyon");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CDG", "Charles de Gaulle", "Francia", "París");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("ORY", "Orly", "Francia", "París");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("ATH", "Eleftherios Venizelos", "Grecia", "Atenas");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("PTP", "Le Raizet", "Guadalupe", "Pointe a Pitre");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("GUA", "La Aurora", "Guatemala", "Guatemala");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CAY", "Rochambeau", "Guyana Fr.", "Cayenne");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("GEO", "Cheddi Jagan", "Guyana", "Georgetown");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("PAP", "International", "Haiti", "Port");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("HNL", "International", "Hawaii", "Honolulu");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("AMS", "Schiphol Int.", "Holanda", "Amsterdam");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("RTM", "Rotterdam", "Holanda", "Rotterdam");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("TGU", "Toncontin", "Honduras", "Tegucigalpa");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("HKG", "International", "Hong Kong", "Hong Kong");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BUD", "Ferihegy", "Hungría", "Budapest");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CCU", "Netaji Subhas", "India", "Calcuta");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("DEL", "Indira Gandhi Int.", "India", "Nueva Delhi");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BOM", "Bombay", "India", "Mumbay");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CGK", "Soekarno-Hatta Int.", "Indonesia", "Jakarta");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("HLP", "Halim Perdana Kusama", "Indonesia", "Jakarta");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BFS", "Belfast Int.", "Inglaterra", "Belfast");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BHD", "Belfast City", "Inglaterra", "Belfast");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("LHR", "Heathrow", "Inglaterra", "Londres");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("LGW", "Gatwick", "Inglaterra", "Londres");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("STN", "Stansted", "Inglaterra", "Londres");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("LTN", "Luton Int.", "Inglaterra", "Londres");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("LCY", "London City Airport", "Inglaterra", "Londres");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("THR", "Mehrabad", "Irán", "Teherán");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SNN", "Shannon", "Irlanda", "Shannon");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("TLV", "Ben Gurion", "Israel", "Tel Aviv");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BRI", "Palese", "Italia", "Bari");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("GOA", "C. Colombo", "Italia", "Génova");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("LIN", "Linate", "Italia", "Milán");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("MXP", "Malpensa", "Italia", "Milán");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("NAP", "Nápoles Int.", "Italia", "Nápoles");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("FCO", "Fiumicino", "Italia", "Roma");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("KIN", "Norman Manley", "Jamaica", "Kingston");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("KTP", "Tinson", "Jamaica", "Kingston");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("NGO", "Komaki", "Japón", "Nagoya");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("KIX", "Kansai Int.", "Japón", "Osaka");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("HND", "Haneda", "Japón", "Tokio");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("NRT", "Narita", "Japón", "Tokio");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("NBO", "Kenyatta", "Kenya", "Nairobi");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("WIL", "Wilson", "Kenya", "Nairobi");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BEY", "International", "Líbano", "Beirut");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("KUL", "International", "Malasia", "Kuala Lumpur");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("MLA", "Luqa", "Malta", "Malta");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CMN", "Mohammed V", "Marruecos", "Casablanca");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("RBA", "Sale", "Marruecos", "Rabat");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("ACA", "Int. Gral. Juan Alvarez", "México", "Acapulco");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CUN", "Cancún", "México", "Cancún");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("MID", "Manuel Rejon", "México", "Mérida");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("MEX", "Benito Juárez", "México", "México");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("RGN", "Yangon Int.", "Myanmar", "Yangon");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("MGA", "Int. Augusto Sandino", "Nicaragua", "Managua");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("L0S", "Murtala Muhammed", "Nigeria", "Lagos");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BGO", "Flesland", "Noruega", "Bergen");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("FBU", "Fornebu", "Noruega", "Oslo");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("GEN", "Gardermoen", "Noruega", "Oslo");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("AKL", "International", "Nva. Zelanda", "Auckland");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("KHI", "Quaid - E - Azam", "Pakistán", "Karachi");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("PTY", "Tocumen Intl.", "Panamá", "Panamá");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("ASU", "Silvio Pettirossi", "Paraguay", "Asunción");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("IQT", "Fco. Secada", "Perú", "Iquitos");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("LIM", "Jorge Chavez Int.", "Perú", "Lima");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("PIU", "Piura", "Perú", "Piura");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("PPT", "Faaa", "Polinesia Fr.", "Papete");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("WAW", "Okecie", "Polonia", "Varsovia");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("LIS", "Lisboa", "Portugal", "Lisboa");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("OPO", "Porto", "Portugal", "Oporto");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SJU", "L. Muñóz Marín Int.", "Puerto Rico", "San Juan");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SIG", "Isla Grande", "Puerto Rico", "San Juan");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("PRG", "Ruzyne", "Rep. Checa", "Praga");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SDQ", "Las Américas", "R. Dominicana", "Sto. Domingo");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BBU", "Baneasa", "Rumania", "Bucarest");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("OTP", "Otopeni", "Rumania", "Bucarest");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("DKR", "Yoff", "Senegal", "Dakar");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SIN", "Changi", "Singapur", "Singapur");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CPT", "Capetown Int.", "Sudáfrica", "Ciudad del Cabo");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("JNB", "Johannesburg Int.", "Sudáfrica", "Johannesburgo");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("ARN", "Arlanda", "Suecia", "Estocolmo");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BMA", "Bromma", "Suecia", "Estocolmo");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BSL", "Basel", "Suiza", "Basilea");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("GVA", "Geneva/Cointrin", "Suiza", "Ginebra");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("ZRH", "Zurich", "Suiza", "Zurich");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("PMB", "Zanderij", "Surinam", "Paramaribo");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("TPE", "Chiang Kai Shek Int.", "Taiwan", "Taipei");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BKK", "International", "Tailandia", "Bangkok");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("POS", "Piarco", "Trinidad", "Puerto España");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("IST", "Ataturk", "Turquía", "Estambul");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("KBP", "Borispol", "Ucrania", "Kiev");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("IEV", "Zhulhany", "Ucrania", "Kiev");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("MVD", "Carrasco", "Uruguay", "Montevideo");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("MCO", "International", "USA", "Orlando");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("ATL", "Hartsfield Int.", "USA", "Atlanta");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BWI", "Baltimore-Wash Int.", "USA", "Baltimore");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BOS", "Logan Int.", "USA", "Boston");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("ORD", "O'Hare-International", "USA", "Chicago");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("MDW", "Midway", "USA", "Chicago");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CGX", "Meigs Field", "USA", "Chicago");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("DFW", "Dallas/Ft.Worth Int.", "USA", "Dallas");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("HOU", "Hobby", "USA", "Houston");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("IAH", "George Bush", "USA", "Houston");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("LAX", "International", "USA", "Los Angeles");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("MEM", "International", "USA", "Memphis");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("MIA", "International", "USA", "Miami");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("MSY", "International", "USA", "Nueva Orleans");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("JFK", "J. F. Kennedy Int.", "USA", "Nueva York");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("LGA", "La Guardia", "USA", "Nueva York");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("EWR", "Newark Int.", "USA", "Nueva York");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("PDX", "International", "USA", "Portland");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SFO", "International", "USA", "San Francisco");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("OAK", "Metropolitan Oakland", "USA", "San Francisco");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("SEA", "Seattle-Tacoma Int.", "USA", "Seattle");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("IAD", "Dulles Int.", "USA", "Washington");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("DCA", "Reagan Int.", "USA", "Washington");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BTW", "Baltimore/Washington Int.", "USA", "Washington");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("CCS", "Simón Bolívar", "Venezuela", "Caracas");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("MAR", "La Chinita", "Venezuela", "Maracaibo");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("PMV", "Del Caribe", "Venezuela", "Porlamar");
insert into Aeropuertos (Codigo, Nombre, Pais, Ciudad) values ("BEG", "Belgrade", "Yugoslavia", "Belgrado");