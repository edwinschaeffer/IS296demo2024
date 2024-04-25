var markers;
function initContent(potentialLead) {
	console.log(potentialLead);
	var div = '<div>' +
		'<div class="header">' + potentialLead.id + '</div>' +
		'<div class="header">' + potentialLead.company + '</div>' +
		'<table class="marker">' +
		'<tr>' +
		'<td>' + potentialLead.street + '</td>' +
		'<td></td>' +
		'</tr>' +
		'<tr>'
		+ '<td>' + potentialLead.city + ', ' + potentialLead.state + ' ' + potentialLead.zipCode + '</td>' +
		'<td></td>' +
		'</tr>' +
		'<tr>' +
		'<td>' + 'Sector: ' + '</td>'
		+ '<td>' + potentialLead.sector + '</td>' + '</tr>' +
		'<tr>' +
		'<td>' + 'Industry' + '</td>' +
		'<td>' + potentialLead.industry + '</td>' +
		'</tr>' +
		'<tr>'
		+ '<td>' + 'Employee Count' + '</td>' +
		'<td>' + potentialLead.employeeCount + '</td>' +
		'</tr>' +
		'<tr>'
		+ '<td>' + 'Age of Business' + '</td>' +
		'<td>' + potentialLead.ageOfBusiness + '</td>' +
		'</tr>' +
		'<tr>'
		+ '<td>' + 'Website' + '</td>' +
		'<td>' + potentialLead.website + '</td>' +
		'</tr>' +
		'</table>'
		+ '<table>' +
		'<tr>' +
		'<td>' + '<button style="margin-top: 10px; margin-left: 65px;" id=' + potentialLead.id + ' onclick="reLocate(\'editStandardById/\' + this.id)">Edit Record Standard</button>' + '</td>' +
		'<td>' + '<button style="margin-top: 10px; margin-left: 20px;" id=' + potentialLead.id + ' onclick="reLocate(\'editThymeLeaf/\' + this.id)">Edit Record ThymeLeaf</button>' + '</td>' +
		'</tr>' +
		'</table>' + 
		'</div>';
	return div;
}
function initMap() {
	
	// The location of stlouis
	var stlouis = {
		lat: 38.5767,
		lng: -92.1735
	};
	// The map, centered at St. Louis
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom: 7,
		center: stlouis
	});
	// The marker, positioned at St. Louis
	/* var marker = new google.maps.Marker({position: stlouis, map: map}); */
	initMarkers(map);
}
function reLocate(url) {
	console.log("my URL: " + url)
	window.location.href = '/' + url;
}
function initMarkers(map) {
	var infowindow = new google.maps.InfoWindow;
	var marker, i;
	for (i = 0; i < markers.length; i++) {
	
		marker = new google.maps.Marker({
			position: new google.maps.LatLng(
				markers[i].potentialLeadLocationLatitude,
				markers[i].potentialLeadLocationLongitude),
			map: map,
		    icon: markers[i].newLead ? { url: "http://maps.google.com/mapfiles/ms/icons/blue-dot.png" } : { url: "http://maps.google.com/mapfiles/ms/icons/red-dot.png" }
		});
		google.maps.event.addListener(marker, 'click', (function(marker, i) {
			return function() {
				infowindow.setContent(initContent(markers[i]));
				infowindow.open(map, marker);
			}
		})(marker, i));
	}
}