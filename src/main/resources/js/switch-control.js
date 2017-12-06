(function(AJS,$){
    AJS.toInit(function(){
        $('.com-mesilat-switch-control').each(function(){
            var toggle = this, $toggle = $(this);
            var options = $.extend(true,
                {
                    check: function(){
                        toggle.checked = true;
                    },
                    uncheck: function(){
                        toggle.checked = false;
                    },
                    isChecked: function(){
                        return toggle.checked;
                    }
                },
                require('com.mesilat:switch-control/' + $toggle.attr('switch-control-plugin-data-macroId'))
            );

            $toggle.on('change', function() {
                if (toggle.checked && 'on' in options && typeof options.on === 'function'){
                    options.on(toggle);
                }
                if (!toggle.checked && 'off' in options && typeof options.off === 'function'){
                    options.off(toggle);
                }
            });

            if ('status' in options && typeof options.status === 'function'){
                options.status(toggle);
            }
        });
    });
})(AJS,AJS.$||$);