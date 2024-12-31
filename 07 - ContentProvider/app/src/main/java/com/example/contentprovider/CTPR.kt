package com.example.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns
import android.provider.ContactsContract

class CTPR : ContentProvider() {

    companion object {
        private const val CONTACTS = 1
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(
                ContactsProviderContract.AUTHORITY,
                ContactsProviderContract.Contacts.TABLE_NAME,
                CONTACTS
            )
        }
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        if (uriMatcher.match(uri) == CONTACTS) {
            val contentResolver = context?.contentResolver
            return contentResolver?.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                ),
                null,
                null,
                sortOrder
            )
        }
        throw IllegalArgumentException("Unknown URI: $uri")
    }

    override fun getType(uri: Uri): String? {
        throw UnsupportedOperationException("Insert operation is not supported")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw UnsupportedOperationException("Insert operation is not supported")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        throw UnsupportedOperationException("Insert operation is not supported")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        throw UnsupportedOperationException("Insert operation is not supported")
    }

    override fun getTypeAnonymous(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            CONTACTS -> "vnd.android.cursor.dir/vnd.${ContactsProviderContract.AUTHORITY}.${ContactsProviderContract.Contacts.TABLE_NAME}"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }


    object ContactsProviderContract {
        const val AUTHORITY = "com.example.contactsprovider"
        val BASE_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY")

        object Contacts : BaseColumns {
            const val TABLE_NAME = "contacts"
            val CONTENT_URI: Uri = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_NAME)

            const val COLUMN_DISPLAY_NAME = "display_name"
            const val COLUMN_PHONE_NUMBER = "phone_number"
        }
    }
}