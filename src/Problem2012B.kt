import java.io.File
import java.net.InetAddress


fun main() {
    val hostName = InetAddress.getLocalHost().hostName
    val isLocal = if (hostName.startsWith("LAPTOP-")) true else false
    val fileName = if (isLocal) "./resources/Problem2012B.txt" else ""
    val fileLinesIterator = if (isLocal) File(fileName).readLines().iterator() else ArrayList<String>().iterator()
    val printList = ArrayList<String>()
    fun readCp(): String {
        return if (isLocal) {
            fileLinesIterator.next()
        } else {
            readln()
        }
    }

    fun writeCp(obj: Any) {
        if (isLocal) {
            printList.add(obj.toString())
        } else {
            println(obj.toString())
        }
    }

    fun assertWrite() {
        val printListIterator = printList.iterator()
        if (isLocal) {
            while (fileLinesIterator.hasNext()) {
                val expected = fileLinesIterator.next()
                val obtained = printListIterator.next()

                if (expected == obtained) {
                    println("Pass")
                } else {
                    println("Failed. Expected $expected, obtained $obtained")
                }
            }
        }
    }

    fun isSquare(numm: Int): Boolean {
        val sqr = Math.sqrt(numm.toDouble()).toInt()
        return sqr * sqr == numm
    }

    repeat(readCp().toInt()) {
        val len = readCp().toInt()
        val arr = readCp().toCharArray().map { it.toChar() == '1' }.toBooleanArray()
        if(!isSquare(len)) {
            writeCp("No")

        }else {
            val size = Math.sqrt(len.toDouble()).toInt()
            var flag: Boolean = false
            for ((index, a) in arr.withIndex()) {
                val q = (index / size).toInt()
                val r = index % size
                val oneCond = (q == 0 || q == size - 1 || r == 0 || r == size - 1)
                if ((a && (!oneCond)) || (!a && (oneCond))) {
                    flag = true
                    break
                }
            }
            val res = if (flag) "No" else "Yes"
            writeCp(res)
        }
    }
    assertWrite()
}