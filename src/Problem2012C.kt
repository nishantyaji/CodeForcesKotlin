import java.io.File
import java.net.InetAddress


fun main() {
    val hostName = InetAddress.getLocalHost().hostName
    val isLocal = if (hostName.startsWith("LAPTOP-")) true else false
    val fileName = if (isLocal) "./resources/Problem2012C.txt" else ""
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



    repeat(readCp().toInt()) {
        val num = readCp().toInt()
        val arr = readCp().split(" ").map { it.toInt() }.toIntArray()

        val counts = arr.toSet()
        if (counts.size == 1) {
            writeCp(0)
        } else {
            val firstElem = arr[0]; val lastElem = arr[num-1]
            var forwardFlag = true; var backwardFlag = true
            var forwardCount = 0; var backwardCount = 0
            for (i in 0 until num) {
                if (forwardFlag && arr[i] == firstElem) {
                    forwardCount++
                } else {
                    forwardFlag = false
                }

                if (backwardFlag && arr[num - 1 - i] == lastElem) {
                    backwardCount++
                } else {
                    backwardFlag = false
                }
                if (!forwardFlag and !backwardFlag) {
                    break
                }
            }
            val deduct =
                if (firstElem == lastElem) (forwardCount + backwardCount) else Math.max(forwardCount, backwardCount)
            writeCp(num - deduct)
        }
    }
    assertWrite()
}