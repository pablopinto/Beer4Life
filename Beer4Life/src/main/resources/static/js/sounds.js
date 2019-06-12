//This will play a random sound
function playRandomSound(){

      //An array to house all of the URLs of your sounds
      var sounds = [ "https://www.elongsound.com/images/mp3/pajaros_noctunos_en_la_lejania_1.mp3",
                     "https://www.elongsound.com/images/mp3/pompa_pajaritos_1.mp3",
                     "https://www.elongsound.com/images/mp3/velocidad_vuelta_larga.mp3",
                     "https://www.elongsound.com/images/mp3/rompiendo_un_aparato_por_la_derecha.mp3"];
      
      //This line will select a random sound to play out of your provided URLS
      var soundFile = sounds[Math.floor(Math.random()*sounds.length)];
      
      //Find the player element that you created and generate an embed file to play the sound within it
      document.getElementById("player").innerHTML="<embed src=\""+soundfile+"\" hidden=\"true\" autostart=\"true\" loop=\"false\" />";
}