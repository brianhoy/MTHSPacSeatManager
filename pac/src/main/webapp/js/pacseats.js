$(document).ready(function() {
  var sc = $('#seat-map').seatCharts({
    naming: {
        top: false,
      getLabel: function(character, row, column) {
        var charDifferences = {
          'm': 2,
          'l': 4,
          'k': 4,
          'j': 2,
          'h': 4,
          'g': 4,
          'f': 2,
          'e': 4,
          'd': 4,
          'c': 2,
          'b': 4,
          'a': 4,
          'M': 0,
          'L': 0,
          'K': 3,
          'J': 3,
          'H': 0,
          'G': 3,
          'F': 2,
          'E': 0,
          'D': 2,
          'C': 3,
          'B': 4,
          'A': 5
        }
        console.log("GetLabel called");
        var dif = charDifferences[character];
        if (character == 'M') {
          if (column > 6) {
            return row + (column - 35);
          }
        }
        return row + (column - dif);
      },
      rows: ["M", "L", "K", "J", "H", "G", "F", "E", "D", "C", "B", "A", "MM", "LL", "KK", "JJ", "HH", "GG", "FF", "EE", "DD", "CC", "BB", "AA"],
    },
    map: [ // max seats in row: 47
      '__mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm___', // m (44 seats)
      '____llllllllllllllllllllllllllllllllllllllll_____', // l (40 seats)
      '____kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk_____', // k (40 seats)
      '__jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj___', // j (44 seats)
      '____hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh_____', // h (40 seats)
      '____gggggggggggggggggggggggggggggggggggggggg_____', // g (40 seats)
      '__ffffffffffffffffffffffffffffffffffffffffffff___', // f (44 seats)
      '____eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee_____', // e (40 seats)
      '____dddddddddddddddddddddddddddddddddddddddd_____', // d (40 seats)
      '__cccccccccccccccccccccccccccccccccccccccccccc___', // c (44 seats)
      '____bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb____', // b (40 seats)
      '____aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa____', // a (40 seats)
      // lower deck
      'MMMMMM___________________________________MMMMMM', // mm (12 seats)
      'LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL_', // ll (46 seats)
      '___KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK____', // kk (40 seats)
      '___JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ___', // jj (41 seats)
      'HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH_', // hh (46 seats)
      '___GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG___', // gg (41 seats)
      '__FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF___', // ff (42 seats)
      'EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE', // ee (47 seats)
      '__DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD____', // dd (42 seats)
      '___CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC___', // cc (43 seats)
      '____BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB____', // bb (39 seats)
      '_____AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA______' // aa (36 seats)


    ],
    seats: {
      a: {
        price: 99.99,
        classes: 'front-seat' //your custom CSS class
      }

    },
    click: function() {
      if (this.status() == 'available') {
        //do some stuff, i.e. add to the cart
        return 'selected';
      } else if (this.status() == 'selected') {
        //seat has been vacated
        return 'available';
      } else if (this.status() == 'unavailable') {
        //seat has been already booked
        return 'unavailable';
      } else {
        return this.style();
      }
    }
  });
});