$(document).ready(function () {
    // Initialize modals
    $('.modal').modal();
});

// Initialize the carousel when the document is ready
$(document).ready(function() {
    $('#carouselExample').carousel();
});

// Pause the carousel when a slide is clicked
$('#carouselExample').on('slide.bs.carousel', function () {
    $(this).carousel('pause');
});