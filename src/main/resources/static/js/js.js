var currYear = (new Date()).getFullYear();
$(document).ready(function() {
    $(".dropdown-trigger").dropdown();
    $(document).ready(function(){
        $('select').formSelect();
    });
    $(document).ready(function() {
        $('input#input_text, textarea#textarea2').characterCounter();
    });
    $(".datepicker").datepicker({
      defaultDate: new Date(currYear-5,1,31),
      // setDefaultDate: new Date(2000,01,31),
      maxDate: new Date(currYear-5,12,31),
      yearRange: [currYear, currYear-2],
      format: "yyyy-mm-dd"    
    });
  });