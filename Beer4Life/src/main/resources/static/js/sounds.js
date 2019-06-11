//This will play a random sound
function playRandomSound(){

      //An array to house all of the URLs of your sounds
      var sounds = [ "http://www.mysite.com/1.wav",
                     "http://www.mysite.com/2.wav",
                     "http://www.mysite.com/3.wav",
                     "http://www.mysite.com/4.wav",
                     "http://www.mysite.com/5.wav",
                     "http://www.mysite.com/6.wav",
                     "http://www.mysite.com/7.wav",
                     "http://www.mysite.com/8.wav"];
      
      //This line will select a random sound to play out of your provided URLS
      var soundFile = sounds[Math.floor(Math.random()*sounds.length)];
      
      //Find the player element that you created and generate an embed file to play the sound within it
      document.getElementById("player").innerHTML="<embed src=\""+soundfile+"\" hidden=\"true\" autostart=\"true\" loop=\"false\" />";
}