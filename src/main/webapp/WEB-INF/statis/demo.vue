<div id="app">
    <p>123</p>
</div>
<script id="tpl" type="x-template">
    <div class='tpl'>
        　　 <p>This is a tpl from script tag</p>
    </div>
</script>
<script type="text/javascript">
    var vm = new Vue({
        el : '#app',
        template : '#tpl'
    });
</script>