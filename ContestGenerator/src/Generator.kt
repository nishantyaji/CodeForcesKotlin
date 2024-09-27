import java.io.File

val codeFolder = "./src/"
val textFolder = "./resources/"

val contest = "2012"
val problems = listOf("B", "C", "D", "E", "F", "G", "H")

fun main() {

    for(p in problems) {
        var codeFile = "${codeFolder}/Problem${contest}${p}.kt"
        var textFile = "${textFolder}Problem${contest}${p}.txt"

        val text = File("./ContestGenerator/src/template.txt").readText()
        val newText = text.replace("zzz.zzz", "Problem${contest}${p}.txt")

        File(codeFile).printWriter().use { out ->
            out.print(newText)
        }
        File(textFile).printWriter().use { out ->
            out.print("")
        }
    }

    println("Hello World!")
}