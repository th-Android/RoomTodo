package si.um.feri.roomtodo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
class Todo(@PrimaryKey @ColumnInfo(name = "todo") val todo: String)