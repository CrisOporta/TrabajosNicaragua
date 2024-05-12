-- Tabla de Usuarios
CREATE TABLE Usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    apellido TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    contraseña TEXT NOT NULL,
    rol TEXT CHECK(rol IN ('reclutador', 'postulante')) NOT NULL,
    verificado BOOLEAN DEFAULT 0
);

-- Tabla de Empleos
CREATE TABLE Empleos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo TEXT NOT NULL,
    descripcion TEXT NOT NULL,
    ubicacion TEXT NOT NULL,
    salario DECIMAL NOT NULL,
    requisitos TEXT NOT NULL,
    reclutador_id INTEGER NOT NULL,
    FOREIGN KEY (reclutador_id) REFERENCES Usuarios(id)
);

-- Tabla de Documentos
CREATE TABLE Documentos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    usuario_id INTEGER NOT NULL,
    archivo BLOB NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id)
);

-- Tabla de Favoritos
CREATE TABLE Favoritos (
    usuario_id INTEGER NOT NULL,
    empleo_id INTEGER NOT NULL,
    PRIMARY KEY (usuario_id, empleo_id),
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id),
    FOREIGN KEY (empleo_id) REFERENCES Empleos(id)
);

-- Tabla de Historial de Búsqueda
CREATE TABLE HistorialBusqueda (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    usuario_id INTEGER NOT NULL,
    terminos_busqueda TEXT,
    filtros_busqueda TEXT,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id)
);

-- Tabla de Entrevistas
CREATE TABLE Entrevistas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    usuario_id INTEGER NOT NULL,
    empleo_id INTEGER NOT NULL,
    fecha TEXT NOT NULL,
    hora TEXT NOT NULL,
    ubicacion TEXT,
    estado TEXT DEFAULT 'Pendiente',
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id),
    FOREIGN KEY (empleo_id) REFERENCES Empleos(id)
);

-- Tabla de Asesoramiento
CREATE TABLE Asesoramiento (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    usuario_id INTEGER NOT NULL,
    consulta TEXT NOT NULL,
    respuesta TEXT,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id)
);

-- Tabla de Perfiles Verificados
CREATE TABLE PerfilesVerificados (
    usuario_id INTEGER PRIMARY KEY,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id)
);

-- Tabla de Alertas de Empleo
CREATE TABLE AlertasEmpleo (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    usuario_id INTEGER NOT NULL,
    terminos_busqueda TEXT,
    filtros_busqueda TEXT,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id)
);

-- Tabla de Anomalías
CREATE TABLE Anomalias (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    usuario_id INTEGER NOT NULL,
    descripcion TEXT NOT NULL,
    captura_pantalla BLOB,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id)
);

-- Tabla de Idiomas
CREATE TABLE Idiomas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    codigo TEXT NOT NULL UNIQUE
);

-- Tabla de Traducciones
CREATE TABLE Traducciones (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    idioma_id INTEGER NOT NULL,
    etiqueta TEXT NOT NULL,
    texto TEXT NOT NULL,
    FOREIGN KEY (idioma_id) REFERENCES Idiomas(id)
);

-- Tabla de Categorías de Empleo
CREATE TABLE CategoriasEmpleo (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL
);

-- Tabla intermedia para Empleos y Categorías
CREATE TABLE EmpleosCategorias (
    empleo_id INTEGER NOT NULL,
    categoria_id INTEGER NOT NULL,
    PRIMARY KEY (empleo_id, categoria_id),
    FOREIGN KEY (empleo_id) REFERENCES Empleos(id),
    FOREIGN KEY (categoria_id) REFERENCES CategoriasEmpleo(id)
);

-- Tabla de Preguntas de Habilidades
CREATE TABLE PreguntasHabilidades (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    pregunta TEXT NOT NULL
);

-- Tabla de Respuestas de Habilidades
CREATE TABLE RespuestasHabilidades (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    usuario_id INTEGER NOT NULL,
    pregunta_id INTEGER NOT NULL,
    respuesta TEXT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id),
    FOREIGN KEY (pregunta_id) REFERENCES PreguntasHabilidades(id)
);

-- Tabla de ChatBots
CREATE TABLE ChatBots (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    pregunta TEXT NOT NULL,
    respuesta TEXT NOT NULL
);
