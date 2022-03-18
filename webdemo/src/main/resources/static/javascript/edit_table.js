function edit(element){




    var posts = {};
    var tr = jQuery(element).parent().parent();
    if(!tr.hasClass("editing")) {


        tr.addClass("editing");
        tr.find("DIV.td").each(function(){
            if(!jQuery(this).hasClass("action")){

                var value = jQuery(this).text();


                jQuery(this).text("");

                jQuery(this).append('<input type="text" value="'+value+'" />');




            } else {
                jQuery(this).find("BUTTON").text("save");
            }
        });
    } else {
        tr.removeClass("editing");

        var post = {
            id: null,
            title: null,
            text: null,
            // startDate:null,
            public: null
        };

        var postLength = Object.keys(post).length - 1;

        tr.find("DIV.td").each(function(){



            if(!jQuery(this).hasClass("action")){




                var value = jQuery(this).find("INPUT").val();


                if(post["id"] === null) {
                    post["id"] = $(this).attr("id");
                }

                for (item in post) {
                    if(post[item] === null ) {
                        post[item] = value;
                        if(!(post[Object.keys(post)[postLength]] === null)) {

                            var jsonPost = JSON.stringify(post);



                            $.ajax({
                                type: "POST",
                                contentType : 'application/json; charset=utf-8',
                                dataType : 'json',
                                url: "/post/savePost.html",
                                data: JSON.stringify(post),
                                success :function(result) {
                                    alert(JSON.stringify(post));
                                }
                            });

                        }else{
                            break
                        }
                    }
                }
                jQuery(this).text(value);
                jQuery(this).find("INPUT").remove();

            } else {
                jQuery(this).find("BUTTON").text("edit");
            }
        });
    }
}
