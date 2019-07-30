function sendUploadServer(urlUpload, file) {
        $.ajax({
            headers: {
                "Content-Range": "bytes "+ "0-" + (file.size - 1) + "/" +(file.size),
                "Content-Type": "application/json; charset=UTF-8",
                "X-Upload-Content-Length": file.size,
                "X-Upload-Content-Type": file.type
            },
            method: "put",
            url: urlUpload,
            crossDomain: true,
            dataType: file.type,
            data: file,
            processData:false
        }).done(function (result) {
            console.log(result);
        });
    }

$(document).ready(function(){
    $('#form-upload-file #inputListFile').change(function () {
        $('#form-upload-file p').text(this.files.length + " file(s) selected");

        $('#list-file').empty();

        var i;
        for(i = 0; i < this.files.length; i++) {
            var node = document.createElement("li");                 // Create a <li> node
            var textnode = document.createTextNode(this.files.item(i).name);         // Create a text node
            node.appendChild(textnode);                              // Append the text to <li>
            $('#list-file').append(node);     // Append <li> to <ul> with id="myList"
        }
    });

    $('#btn-upload').click(function () {
        var listFile = document.getElementById('inputListFile').files;

        if (listFile.length > 5) {
            alert('Chỉ được phép upload 5 file');
            return;
        }
        var j;
        for(j = 0; j < listFile.length; j++) {
            var fileItem = listFile.item(j);
            var dataReq = {
                "file_name" : fileItem.name,
                "file_size" : fileItem.size,
                "mime_type" : fileItem.type
            };

            $.ajax({
                headers: {
                    "Content-Type": "application/json"
                },
                url: "/upload/check-hash",
                type: "post",
                dataType: "text",
                data: JSON.stringify(dataReq)
            }).done(function (result) {
                var dataJs = JSON.parse(result);
                var urlUpload = dataJs['url_upload'];
                sendUploadServer(urlUpload, fileItem);
            });
        }
    });

});
