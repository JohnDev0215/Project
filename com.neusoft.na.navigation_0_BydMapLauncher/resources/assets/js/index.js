const searchParams = new URLSearchParams(location.search)
const mode = searchParams.get("mode")
const style = searchParams.get('style')
console.log(mode, "8155 mode",style,"8155 style")

if (mode === "night") {
   document.querySelector("#privacyWarp").classList.add("dark")
}
if (style ==='denza') {
   document.querySelector("#privacyWarp").classList.add("denza")
}
