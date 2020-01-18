const FILE_NAMES = [
    "tinycat.jpg",
    "cute_doggo.jpg",
    "hedgehogs.png",
    "cat_gallery.zip",
    "my_wallpaper.bmp"
];

function load() {
    // Randomize the command block
    randomize();
    setInterval(randomize, 2000);
}

function randomize() {
    let fileName = FILE_NAMES[Math.floor(Math.random() * FILE_NAMES.length)];
    UI.get("command").innerText = "cat " + fileName + " | nc " + window.location.hostname + " 1";
}
