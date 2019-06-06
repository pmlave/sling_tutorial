$(document).ready(() => {
  var keyValueNum = 0;
  $('#new-key-value').on('click', () => {
    var thing = $('#key-value-pair').html();
    thing = thing.replace(new RegExp("{{number}}", "g"), keyValueNum);
    $('#key-value-dest').append(thing);
    keyValueNum++;
  });
});
