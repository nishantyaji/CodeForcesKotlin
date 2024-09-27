import java.io.File
import java.net.InetAddress


fun main() {
    val hostName = InetAddress.getLocalHost().hostName
    val isLocal = if(hostName.startsWith("LAPTOP-")) true else false
    val fileName = if(isLocal) "./resources/Problem2012A.txt" else ""
    val fileLinesIterator = if(isLocal) File(fileName).readLines().iterator() else ArrayList<String>().iterator()
    val printList = ArrayList<String>()
    fun readCp(): String {
        return if(isLocal) {
            fileLinesIterator.next()
        } else{
            readln()
        }
    }

    fun writeCp(obj: Any){
        if(isLocal) {
            printList.add(obj.toString())
        } else{
            println(obj.toString())
        }
    }

    fun assertWrite() {
        val printListIterator = printList.iterator()
        if(isLocal) {
            while(fileLinesIterator.hasNext()) {
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
        val arr = readCp().split(" ").map { it.toInt() }.toIntArray()
        if(arr[0] > arr[1]) {
            arr[0] = arr[1].also { arr[1] = arr[0] }
        }
        writeCp(arr[0].toString() + " " + arr[1].toString())
    }
    assertWrite()
}