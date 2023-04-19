$(document).ready(function() {
    var i = 0;

    // Crear un ícono personalizado para el marcador del auto
    var autoIcon = L.icon({
        iconUrl: 'images/car.png',  // Ruta al archivo de imagen del ícono del auto
        iconSize: [50, 50],  // Tamaño del ícono del auto en píxeles
    });

    var map = L.map('map').setView([originPosition.latitude, originPosition.longitude], 13);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© OpenStreetMap contributors'
    }).addTo(map);

    // Aumentar el zoom del mapa
    map.zoomIn(6);

    var marker = L.marker([originPosition.latitude, originPosition.longitude], {icon: autoIcon}).addTo(map);

    // Asignar un evento de movimiento al marcador
    marker.on('move', function(e) {
        var newLatLng = e.latlng; // Nueva posición del marcador
        map.panTo(newLatLng); // Mover el mapa a la nueva posición del marcador
    });
    function moveMarker() {
        // Simular movimiento del marcador
        if(coordenates.length > 0 && coordenates.length >= (i+1)) {
            marker.setLatLng([coordenates[i].latitude, coordenates[i].longitude])
                .update();

            i++;
        } else {
            clearInterval(intervalId);
        }

    }

    var intervalId = setInterval(moveMarker, 2000); // Actualizar posición cada 2 segundos

});