// Config: si este HTML se sirve por el mismo microservicio, deja API_BASE = ''.
const API_BASE = ''; // mismo origen (ej. http://localhost:8083)
const endpoints = {
  listar:   () => `${API_BASE}/disponibilidad/listar`,
  grabar:   () => `${API_BASE}/disponibilidad/grabar`,
  eliminar: (id) => `${API_BASE}/disponibilidad/eliminar/${id}`,
  buscar:   (id) => `${API_BASE}/disponibilidad/buscar/${id}`,
  actualizar:(id)=> `${API_BASE}/disponibilidad/actualizar/${id}`,
};

const $ = (s)=>document.querySelector(s);
const $$ = (s)=>document.querySelectorAll(s);
const tbody = $("#tbody");
const badgeCount = $("#badgeCount");
const form = $("#formDis");
const btnReload = $("#btnReload");
const txtFiltro = $("#txtFiltro");

let cache = [];

async function listar() {
  tbody.innerHTML = `<tr><td colspan="6" class="muted">Cargando…</td></tr>`;
  try {
    const res = await fetch(endpoints.listar());
    if (!res.ok) throw new Error(await res.text());
    cache = await res.json();
    render(cache);
  } catch (e) {
    tbody.innerHTML = `<tr><td colspan="6" class="muted">Error: ${e.message}</td></tr>`;
  }
}

function render(rows) {
  const filtro = (txtFiltro.value || '').trim().toLowerCase();
  const data = rows.filter(r => !filtro || (r.codMedico || '').toLowerCase().includes(filtro));
  badgeCount.textContent = `${data.length} registro${data.length!==1?'s':''}`;
  if (data.length === 0) {
    tbody.innerHTML = `<tr><td colspan="6" class="muted">Sin registros</td></tr>`;
    return;
  }
  tbody.innerHTML = data.map(r => `
    <tr>
      <td>${r.id ?? ''}</td>
      <td><span class="pill">${r.codMedico ?? ''}</span></td>
      <td>${r.fec ?? ''}</td>
      <td>${r.horIni ?? ''}</td>
      <td>${r.horFin ?? ''}</td>
      <td style="text-align:right">
        <button class="btn btn-danger" data-del="${r.id}">Eliminar</button>
      </td>
    </tr>
  `).join('');
  // bind deletes
  $$("#tbody [data-del]").forEach(btn=>{
    btn.addEventListener('click', async () => {
      if(!confirm('¿Eliminar disponibilidad?')) return;
      const id = btn.getAttribute('data-del');
      try{
        const res = await fetch(endpoints.eliminar(id), { method: 'DELETE' });
        if (!res.ok) throw new Error(await res.text());
        cache = cache.filter(x => String(x.id) !== String(id));
        render(cache);
      }catch(e){ alert(`Error: ${e.message}`); }
    });
  });
}

form.addEventListener('submit', async (ev) => {
  ev.preventDefault();
  const fd = new FormData(form);
  const body = Object.fromEntries(fd.entries());
  // Validación rápida
  if(!body.codMedico || !body.fec || !body.horIni || !body.horFin){
    alert('Completa todos los campos.'); return;
  }
  $("#btnCrear").disabled = true;
  try{
    const res = await fetch(endpoints.grabar(), {
      method: 'POST',
      headers: {'Content-Type':'application/json'},
      body: JSON.stringify(body)
    });
    if (!res.ok) throw new Error(await res.text());
    const nuevo = await res.json();
    cache.push(nuevo);
    form.reset();
    render(cache);
  }catch(e){ alert(`Error: ${e.message}`); }
  finally{ $("#btnCrear").disabled = false; }
});

btnReload.addEventListener('click', listar);
txtFiltro.addEventListener('input', ()=>render(cache));

// inicio
listar();
