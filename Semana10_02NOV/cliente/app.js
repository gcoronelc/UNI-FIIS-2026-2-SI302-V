// ---------------------------------------------------------------
// Configuración
// ---------------------------------------------------------------
// Si esta página se sirve desde el mismo proyecto Spring Boot
// (src/main/resources/static), deje API_BASE vacío (mismo origen).
// Si se abre desde otro origen (archivo local, Live Server, etc.),
// indique la URL del backend, por ejemplo: "http://localhost:8080".
const API_BASE = "http://localhost:8080";
const ENDPOINT = "/api/consultas/cursos";
const MONEDA = "S/"; // Ajustar según la moneda de la base de datos.

// ---------------------------------------------------------------
// Referencias al DOM
// ---------------------------------------------------------------
const form = document.getElementById("formConsulta");
const inputId = document.getElementById("idCurso");
const btn = document.getElementById("btnConsultar");
const resultado = document.getElementById("resultado");

// ---------------------------------------------------------------
// Utilidades de presentación
// ---------------------------------------------------------------
function mostrar(tipo, contenidoHTML) {
  resultado.className = "resultado " + tipo;
  resultado.innerHTML = contenidoHTML;
}

function escapar(texto) {
  const div = document.createElement("div");
  div.textContent = texto;
  return div.innerHTML;
}

function formatearPrecio(valor) {
  return (
    MONEDA +
    " " +
    Number(valor).toLocaleString("es-PE", {
      minimumFractionDigits: 2,
      maximumFractionDigits: 2,
    })
  );
}

// ---------------------------------------------------------------
// Consulta al servicio REST
// ---------------------------------------------------------------
async function consultarPrecio(idCurso) {
  const url = `${API_BASE}${ENDPOINT}/${encodeURIComponent(idCurso)}`;
  const respuesta = await fetch(url, { method: "GET" });

  if (respuesta.ok) {
    // El servicio retorna un Double (200 OK).
    const precio = await respuesta.json();
    return { estado: 200, precio };
  }

  // 404 y 500 retornan un mensaje de texto plano.
  const mensaje = await respuesta.text();
  return { estado: respuesta.status, mensaje };
}

form.addEventListener("submit", async (evento) => {
  evento.preventDefault();

  const valor = inputId.value.trim();
  const idCurso = Number(valor);

  if (valor === "" || !Number.isInteger(idCurso) || idCurso <= 0) {
    mostrar("aviso", "Ingrese un ID de curso válido (entero positivo).");
    inputId.focus();
    return;
  }

  btn.disabled = true;
  mostrar("cargando", "Consultando…");

  try {
    const r = await consultarPrecio(idCurso);

    if (r.estado === 200) {
      mostrar(
        "exito",
        `Precio del curso con ID ${idCurso}:
         <span class="monto">${formatearPrecio(r.precio)}</span>`
      );
    } else if (r.estado === 404) {
      mostrar("aviso", escapar(r.mensaje || `El curso con ID ${idCurso} no fue encontrado.`));
    } else {
      mostrar("error", escapar(r.mensaje || `Error del servidor (HTTP ${r.estado}).`));
    }
  } catch (error) {
    // Falla de red, servidor apagado o bloqueo por CORS.
    mostrar(
      "error",
      "No fue posible comunicarse con el servicio. Verifique que el backend " +
        "esté en ejecución y que CORS esté habilitado si la página se sirve desde otro origen."
    );
    console.error(error);
  } finally {
    btn.disabled = false;
  }
});
